plugins {
    id("java")
    `maven-publish`
    signing
}

sourceSets {
    main {
        java {
            srcDir("src/main")
        }
    }
}

repositories {
    mavenCentral()
}

description = "Particle Effects for WorldSeed"
java.sourceCompatibility = JavaVersion.VERSION_25

tasks.jar {
    manifest {
        archiveBaseName.set("ParticleEmitter")
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        groupId = "net.worldseed.particleemitter"
        artifactId = "ParticleEmitter"
        version = "1.4.1"

        from(components["java"])
    }

    repositories {
        maven {
            name = "WorldSeed"
            url = uri("https://reposilite.worldseed.online/public")
            credentials(PasswordCredentials::class)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:6.1.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:6.1.0")

    compileOnly("net.minestom:minestom:2026.06.20-26.1.2")
    testImplementation("net.minestom:minestom:2026.06.20-26.1.2")

    implementation("dev.hollowcube:mql:1.0.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}