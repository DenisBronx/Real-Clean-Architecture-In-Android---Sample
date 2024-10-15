package com.denisbrandi.androidrealca.plp.presentation.viewmodel

import com.denisbrandi.androidrealca.coroutines.testdispatcher.MainCoroutineRule
import com.denisbrandi.androidrealca.flow.testobserver.*
import com.denisbrandi.androidrealca.foundations.Answer
import com.denisbrandi.androidrealca.money.domain.model.Money
import com.denisbrandi.androidrealca.product.domain.model.Product
import com.denisbrandi.androidrealca.product.domain.usecase.GetProducts
import com.denisbrandi.androidrealca.user.domain.model.User
import com.denisbrandi.androidrealca.user.domain.usecase.GetUser
import com.denisbrandi.androidrealca.viewmodel.StateDelegate
import com.denisbrandi.androidrealca.wishlist.domain.model.WishlistItem
import com.denisbrandi.androidrealca.wishlist.domain.usecase.*
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.Assert.*

class RealPLPViewModelTest {

    @get:Rule
    val rule = MainCoroutineRule()

    private val getUser = GetUser { USER }
    private val getProducts = TestGetProducts()
    private val observeUserWishlistIds = TestObserveUserWishlistIds()
    private val addToWishlist = TestAddToWishlist()
    private val removeFromWishlist = TestRemoveFromWishlist()
    private val stateDelegate = StateDelegate<PLPState>()
    private lateinit var stateObserver: FlowTestObserver<PLPState>
    private lateinit var sut: RealPLPViewModel

    @Before
    fun setUp() {
        sut = RealPLPViewModel(
            getUser,
            getProducts,
            observeUserWishlistIds,
            addToWishlist,
            removeFromWishlist,
            stateDelegate
        )
        stateObserver = sut.state.test()
    }

    @Test
    fun `EXPECT default state`() {
        assertEquals(
            listOf(PLPState(fullName = NAME)),
            stateObserver.getValues()
        )
    }

    @Test
    fun `EXPECT loading state WHEN waiting for use case response`() {
        getProducts.productsResult = { awaitCancellation() }

        sut.loadProducts()

        assertEquals(
            listOf(
                PLPState(fullName = NAME),
                PLPState(fullName = NAME, contentType = ContentType.Loading)
            ),
            stateObserver.getValues()
        )
    }

    @Test
    fun `EXPECT error state WHEN use case returns error`() {
        getProducts.productsResult = { Answer.Error(Unit) }

        sut.loadProducts()

        assertEquals(
            listOf(
                PLPState(fullName = NAME),
                PLPState(fullName = NAME, contentType = ContentType.Loading),
                PLPState(fullName = NAME, contentType = ContentType.Error)
            ),
            stateObserver.getValues()
        )
    }

    @Test
    fun `EXPECT content state WHEN use case returns error`() {
        getProducts.productsResult = { Answer.Success(PRODUCTS) }

        sut.loadProducts()

        assertEquals(
            listOf(
                PLPState(fullName = NAME),
                PLPState(fullName = NAME, contentType = ContentType.Loading),
                PLPState(fullName = NAME, contentType = ContentType.Content(PRODUCTS))
            ),
            stateObserver.getValues()
        )
    }

    @Test
    fun `EXPECT wishlist updated WHEN there are updates`() = runTest {
        val wishlistIds1 = listOf("1")
        val wishlistIds2 = listOf("1", "2")
        val wishlistIds3 = listOf("2")
        observeUserWishlistIds.wishlistUpdates.emit(wishlistIds1)
        observeUserWishlistIds.wishlistUpdates.emit(wishlistIds2)
        observeUserWishlistIds.wishlistUpdates.emit(wishlistIds3)

        assertEquals(
            listOf(
                PLPState(fullName = NAME),
                PLPState(fullName = NAME, wishlistIds = wishlistIds1),
                PLPState(fullName = NAME, wishlistIds = wishlistIds2),
                PLPState(fullName = NAME, wishlistIds = wishlistIds3),
            ),
            stateObserver.getValues()
        )
    }

    @Test
    fun `EXPECT not favourite WHEN wishlist is empty`() = runTest {
        stateDelegate.updateState {
            PLPState(fullName = NAME, contentType = ContentType.Content(PRODUCTS))
        }

        val result = sut.isFavourite("2")

        assertFalse(result)
    }

    @Test
    fun `EXPECT favourite WHEN wishlist has id`() = runTest {
        stateDelegate.updateState {
            PLPState(
                fullName = NAME,
                wishlistIds = listOf("2", "3"),
                contentType = ContentType.Content(PRODUCTS)
            )
        }

        val result = sut.isFavourite("2")

        assertTrue(result)
    }

    @Test
    fun `EXPECT not favourite WHEN wishlist doesn't have id`() = runTest {
        stateDelegate.updateState {
            PLPState(
                fullName = NAME,
                wishlistIds = listOf("2", "3"),
                contentType = ContentType.Content(PRODUCTS)
            )
        }

        val result = sut.isFavourite("1")

        assertFalse(result)
    }

    @Test
    fun `EXPECT item added to wishlist`() {
        sut.addProductToWishlist(PRODUCT)

        assertEquals(listOf(WISHLIST_ITEM), addToWishlist.invocations)
    }

    @Test
    fun `EXPECT item removed from wishlist`() {
        sut.removeProductFromWishlist("1")

        assertEquals(listOf("1"), removeFromWishlist.invocations)
    }

    private class TestGetProducts : GetProducts {
        lateinit var productsResult: suspend () -> Answer<List<Product>, Unit>
        override suspend fun invoke() = productsResult()
    }

    private class TestObserveUserWishlistIds : ObserveUserWishlistIds {
        val wishlistUpdates = MutableStateFlow(emptyList<String>())
        override fun invoke(): Flow<List<String>> = wishlistUpdates
    }

    private class TestAddToWishlist : AddToWishlist {
        val invocations = mutableListOf<WishlistItem>()
        override fun invoke(wishlistItem: WishlistItem) {
            invocations.add(wishlistItem)
        }
    }

    private class TestRemoveFromWishlist : RemoveFromWishlist {
        val invocations = mutableListOf<String>()
        override fun invoke(wishlistItemId: String) {
            invocations.add(wishlistItemId)
        }
    }

    private companion object {
        const val NAME = "Amazing Android Dev"
        val USER = User(id = "", fullName = NAME)
        val PRODUCT = Product(
            "1",
            "Wireless Headphones",
            Money(99.99, "$"),
            "https://m.media-amazon.com/images/I/61fU3njgzZL._AC_SL1500_.jpg"
        )
        val PRODUCTS = listOf(PRODUCT)
        val WISHLIST_ITEM = WishlistItem(
            "1",
            "Wireless Headphones",
            Money(99.99, "$"),
            "https://m.media-amazon.com/images/I/61fU3njgzZL._AC_SL1500_.jpg"
        )
    }
}
