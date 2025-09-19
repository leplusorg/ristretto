/*
 * Copyright 2016-present Thomas Leplus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
const Configuration = {
  /*
   * Inherit rules from conventional commits.
   */
  extends: ["@commitlint/config-conventional"],

  /*
   * Any rules defined here will override rules from parent.
   */
  rules: {
    "body-leading-blank": [2, "always"], // warning -> error
    "body-max-line-length": [1, "always", 100], // error -> warning
    "footer-leading-blank": [2, "always"], // warning -> error
    "footer-max-length": [1, "always", 100], // error -> warning
    "header-max-length": [1, "always", 100], // error -> warning
  },
};

export default Configuration;
