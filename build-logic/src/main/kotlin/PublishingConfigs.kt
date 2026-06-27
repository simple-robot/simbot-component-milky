/*
 *     Copyright (c) 2026. ForteScarlet.
 *
 *     Project    https://github.com/simple-robot/simbot-component-milky
 *     Email      ForteScarlet@163.com
 *
 *     This file is part of simbot-component-milky.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     Lesser GNU General Public License for more details.
 *
 *     You should have received a copy of the Lesser General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import org.gradle.api.Project

fun MavenPublishBaseExtension.configureMilkyPublishing(project: Project) {
    publishToMavenCentral(automaticRelease = true)
    if (!isSimbotLocal()) {
        signAllPublications()
    }
    coordinates(project.group.toString(), project.name, project.version.toString())

    pom {
        name.set(project.provider { project.name })
        description.set(project.provider { project.description ?: P.ComponentMilky.DESCRIPTION })
        url.set(P.ComponentMilky.HOMEPAGE)

        licenses {
            P.ComponentMilky.licenses.forEach { license ->
                license {
                    name.set(license.name)
                    url.set(license.url)
                }
            }
        }

        developers {
            P.ComponentMilky.developers.forEach { developer ->
                developer {
                    id.set(developer.id)
                    name.set(developer.name)
                    email.set(developer.email)
                    url.set(developer.url)
                }
            }
        }

        scm {
            url.set(P.ComponentMilky.HOMEPAGE)
            connection.set("scm:git:${P.ComponentMilky.HOMEPAGE}.git")
            developerConnection.set("scm:git:ssh://git@github.com/simple-robot/simbot-component-milky.git")
        }

        issueManagement {
            system.set("GitHub Issues")
            url.set("${P.ComponentMilky.HOMEPAGE}/issues")
        }
    }
}
