#!/bin/bash
set -e
GIT_DIR=$(git rev-parse --git-dir 2>/dev/null)
GIT_ROOT=$(git rev-parse --show-toplevel 2>/dev/null)

mkdir -p "${GIT_DIR}/hooks/"

cp "${GIT_ROOT}/scripts/hooks/localci.sh" "${GIT_DIR}/hooks/pre-push" &&
   chmod +x "${GIT_DIR}/hooks/pre-push"

echo "Git hooks installed!"
