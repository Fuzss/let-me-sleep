plugins {
    id("fuzs.multiloader.multiloader-convention-plugins-common")
}

dependencies {
    modCompileOnlyApi(sharedLibs.puzzleslib.common)
    compileOnlyApi(sharedLibs.neoforgedatapackextensions.common)
}

multiloader {
    mixins {
        mixin(
            "LivingEntityMixin",
            "MonsterMixin",
            "PlayerMixin",
            "ServerLevelMixin",
            "ServerPlayerMixin"
        )
        clientMixin("CameraMixin")
    }
}
