plugins {
    id("fuzs.multiloader.multiloader-convention-plugins-common")
}

dependencies {
    modCompileOnlyApi(libs.puzzleslib.common)
    modCompileOnlyApi(libs.neoforgedatapackextensions.common)
}

multiloader {
    mixins {
        mixin(
            "BedRule\$RuleMixin",
            "LivingEntityMixin",
            "MonsterMixin",
            "PlayerMixin",
            "ServerLevelMixin",
            "ServerPlayerMixin"
        )
        clientMixin("CameraMixin")
    }
}
