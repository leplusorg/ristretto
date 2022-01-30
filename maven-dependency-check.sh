#!/bin/bash
# Copyright 2016-present Thomas Leplus
# 
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
# 
#     http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

set -euo pipefail
IFS=$'\n\t'

SCRIPT_DIR=$(cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && \pwd)

cd "${SCRIPT_DIR}"

rc=0
dependency_check=0
plugin_check=0
maven_check=0

while read -r l; do
    \echo "${l}"
    if [[ "${l}" == *"[ERROR]"* ]]; then
	rc=$((rc+1))
    elif [[ "${l}" == *"No dependencies in Dependencies have newer versions."* ]]; then
	dependency_check=1
    elif [[ "${l}" == *"All plugins with a version specified are using the latest versions."* ]]; then
	plugin_check=1
    elif [[ "${l}" == *"No plugins require a newer version of Maven than specified by the pom."* ]]; then
	maven_check=1
    fi
done <<< "$(./mvnw versions:display-dependency-updates \
                   versions:display-plugin-updates \
     	           versions:display-property-updates)"

if [[ ("${dependency_check=1}" != 1) ]]; then
    rc=$((rc+1))
fi

if [[ ("${plugin_check=1}" != 1) ]]; then
    rc=$((rc+1))
fi

if [[ ("${maven_check=1}" != 1) ]]; then
    rc=$((rc+1))
fi

exit ${rc}
