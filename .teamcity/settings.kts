import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.CustomChart
import jetbrains.buildServer.configs.kotlin.v2019_2.CustomChart.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.python
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.buildTypeCustomChart
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.githubConnection
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.2"

project {

    buildType(log_upload)
    buildType(Build)

    params {
        param("env.build_log_upload", "log_uppload")
    }

    features {
        feature {
            id = "PROJECT_EXT_5"
            type = "storage_settings"
            param("storage.artifactory.repository.type", "local")
            param("storage.artifactory.url", "http://localhost:8082/artifactory/")
            param("storage.artifactory.username", "admin")
            param("storage.name", "jfrog")
            param("storage.type", "Artifacactory_storage")
            param("storage.artifactory.repository.key", "example-repo-local")
            param("secure:storage.artifactory.password", "credentialsJSON:ec1e89ac-311e-4134-8837-05cb46c139e7")
        }
        buildTypeCustomChart {
            id = "PROJECT_EXT_6"
            title = "New chart title"
            seriesTitle = "Serie"
            format = CustomChart.Format.TEXT
            series = listOf(
                Serie(title = "BuildTestStatus", key = SeriesKey("BuildTestStatus"))
            )
        }
        githubConnection {
            id = "PROJECT_EXT_8"
            displayName = "GitHub.com"
            clientId = "b53265e31eb718398a59"
            clientSecret = "credentialsJSON:d87bf190-94cc-4941-abc0-0d400b048a46"
        }
    }
}

object Build : BuildType({
    name = "Build"

    artifactRules = "**"

    params {
        param("env.build_log_upload", "%system.teamcity.buildConfName%")
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        python {
            command = file {
                filename = "hello.py"
            }
        }
        script {
            scriptContent = """echo "uploaded""""
            param("org.jfrog.artifactory.selectedDeployableServer.publishBuildInfo", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.buildDependencies", "Requires Artifactory Pro.")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.envVarsExcludePatterns", "*password*,*secret*")
            param("org.jfrog.artifactory.selectedDeployableServer.targetRepo", "example-repo-local")
        }
        python {
            command = file {
                filename = "palindrome.py"
            }
        }
        powerShell {
            scriptMode = file {
                path = "upload.ps1"
            }
            noProfile = false
            param("org.jfrog.artifactory.selectedDeployableServer.publishBuildInfo", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.urlId", "1")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecFilePath", "upload.json")
            param("org.jfrog.artifactory.selectedDeployableServer.envVarsExcludePatterns", "*password*,*secret*")
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "File")
            param("org.jfrog.artifactory.selectedDeployableServer.targetRepo", "example-repo-local")
        }
    }

    triggers {
        vcs {
        }
    }
})

object log_upload : BuildType({
    name = "log_upload1"

    artifactRules = ".git => .git"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            scriptContent = """echo "uploading build_log""""
            param("org.jfrog.artifactory.selectedDeployableServer.publishBuildInfo", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpec", """
                {
                  "files": [
                    {
                      "pattern": "D:/demo/logs/teamcity-build.log",
                      "target": "example-repo-local/log/"
                    }
                  ]
                }
            """.trimIndent())
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "true")
            param("org.jfrog.artifactory.selectedDeployableServer.urlId", "1")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecFilePath", "upload_log.json")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "File")
            param("org.jfrog.artifactory.selectedDeployableServer.envVarsExcludePatterns", "*password*,*secret*")
            param("org.jfrog.artifactory.selectedDeployableServer.targetRepo", "example-repo-local")
        }
        script {
            scriptContent = """echo "artifacts build logs uploaded""""
            param("org.jfrog.artifactory.selectedDeployableServer.downloadSpecSource", "Job configuration")
            param("org.jfrog.artifactory.selectedDeployableServer.useSpecs", "false")
            param("org.jfrog.artifactory.selectedDeployableServer.uploadSpecSource", "Job configuration")
        }
    }

    triggers {
        finishBuildTrigger {
            buildType = "${Build.id}"
        }
    }

    dependencies {
        snapshot(Build) {
            runOnSameAgent = true
            reuseBuilds = ReuseBuilds.NO
            onDependencyFailure = FailureAction.IGNORE
            onDependencyCancel = FailureAction.ADD_PROBLEM
        }
    }
})
