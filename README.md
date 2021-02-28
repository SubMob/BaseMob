# BaseMob

[![master](https://github.com/SubMob/BaseMob/actions/workflows/master.yml/badge.svg)](https://github.com/SumMob/BaseMob/actions/workflows/master.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.submob/basemob/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.submob/basemob)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/32600d90388a4f1bb55d45744ee49026)](https://www.codacy.com/gh/SubMob/BaseMob?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=SubMob/BaseMob&amp;utm_campaign=Badge_Grade)

## Install

BaseMob is currently published to Maven Central, so add that to repositories.

```groovy
repositories {
    mavenCentral()
}
```

Then, simply add the dependency to your common source-set dependencies

```groovy
commonMain {
    implementation("com.github.submob:basemob:LATEST_VERSION")
}
```

### License

```markdown
Copyright 2020 Mustafa Ozhan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
