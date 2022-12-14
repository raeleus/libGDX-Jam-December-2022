package com.ray3k.template;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.BoneData;
import com.esotericsoftware.spine.EventData;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SlotData;
import java.lang.String;

public class Resources {
    public static TextureAtlas textures_textures;

    public static Skin skin_skin;

    public static Sound sfx_chapter1;

    public static Sound sfx_chapter2;

    public static Sound sfx_chapter3;

    public static Sound sfx_chapter4;

    public static Sound sfx_chime;

    public static Sound sfx_chord;

    public static Sound sfx_clear;

    public static Sound sfx_click;

    public static Sound sfx_conclusion;

    public static Sound sfx_flagRemove;

    public static Sound sfx_flag;

    public static Sound sfx_highlight;

    public static Sound sfx_horn;

    public static Sound sfx_libgdx00;

    public static Sound sfx_libgdx01;

    public static Sound sfx_libgdx02;

    public static Sound sfx_piano;

    public static Sound sfx_raeleus00;

    public static Sound sfx_raeleus01;

    public static Sound sfx_reset;

    public static Sound sfx_string;

    public static Sound sfx_themedHorror00;

    public static Sound sfx_themedHorror01;

    public static Sound sfx_themedHorror02;

    public static Sound sfx_timerTick;

    public static Sound sfx_violin;

    public static Sound sfx_winSound;

    public static Music bgm_audioSample;

    public static Music bgm_disco;

    public static Music bgm_gameover1;

    public static Music bgm_gameover2;

    public static Music bgm_gameover3;

    public static Music bgm_gameover4;

    public static Music bgm_gameover5;

    public static Music bgm_gameover6;

    public static Music bgm_gameoverbreathing;

    public static Music bgm_gameovercry;

    public static Music bgm_gameoverlever;

    public static Music bgm_gameovermeat;

    public static Music bgm_gameoversaw;

    public static Music bgm_gameoverscream;

    public static Music bgm_gameovershot;

    public static Music bgm_menu;

    public static void loadResources(AssetManager assetManager) {
        textures_textures = assetManager.get("textures/textures.atlas");
        SpineBoy.skeletonData = assetManager.get("spine/boy.json");
        SpineBoy.animationData = assetManager.get("spine/boy.json-animation");
        SpineBoy.animationAnimation = SpineBoy.skeletonData.findAnimation("animation");
        SpineBoy.boneRoot = SpineBoy.skeletonData.findBone("root");
        SpineBoy.boneBoy = SpineBoy.skeletonData.findBone("boy");
        SpineBoy.boneBoy2 = SpineBoy.skeletonData.findBone("boy2");
        SpineBoy.slotWhite = SpineBoy.skeletonData.findSlot("white");
        SpineBoy.slotBoy = SpineBoy.skeletonData.findSlot("boy");
        SpineBoy.slotBoy2 = SpineBoy.skeletonData.findSlot("boy2");
        SpineBoy.skinDefault = SpineBoy.skeletonData.findSkin("default");
        SpineDisco.skeletonData = assetManager.get("spine/disco.json");
        SpineDisco.animationData = assetManager.get("spine/disco.json-animation");
        SpineDisco.animationAnimation = SpineDisco.skeletonData.findAnimation("animation");
        SpineDisco.boneRoot = SpineDisco.skeletonData.findBone("root");
        SpineDisco.boneGameDiscoBody2 = SpineDisco.skeletonData.findBone("game/disco-body2");
        SpineDisco.boneGameDiscoBody = SpineDisco.skeletonData.findBone("game/disco-body");
        SpineDisco.boneGameDiscoHead = SpineDisco.skeletonData.findBone("game/disco-head");
        SpineDisco.boneGameDiscoLimb = SpineDisco.skeletonData.findBone("game/disco-limb");
        SpineDisco.boneGameDiscoLimb2 = SpineDisco.skeletonData.findBone("game/disco-limb2");
        SpineDisco.boneGameDiscoLimb4 = SpineDisco.skeletonData.findBone("game/disco-limb4");
        SpineDisco.boneGameDiscoLimb3 = SpineDisco.skeletonData.findBone("game/disco-limb3");
        SpineDisco.slotGameDiscoBall = SpineDisco.skeletonData.findSlot("game/disco-ball");
        SpineDisco.slotGameDiscoLimb = SpineDisco.skeletonData.findSlot("game/disco-limb");
        SpineDisco.slotGameDiscoLimb4 = SpineDisco.skeletonData.findSlot("game/disco-limb4");
        SpineDisco.slotGameDiscoLimb3 = SpineDisco.skeletonData.findSlot("game/disco-limb3");
        SpineDisco.slotGameDiscoLimb2 = SpineDisco.skeletonData.findSlot("game/disco-limb2");
        SpineDisco.slotGameDiscoBody = SpineDisco.skeletonData.findSlot("game/disco-body");
        SpineDisco.slotGameDiscoHead = SpineDisco.skeletonData.findSlot("game/disco-head");
        SpineDisco.skinDefault = SpineDisco.skeletonData.findSkin("default");
        SpineGameOverEye.skeletonData = assetManager.get("spine/game-over-eye.json");
        SpineGameOverEye.animationData = assetManager.get("spine/game-over-eye.json-animation");
        SpineGameOverEye.animationEyeMovement = SpineGameOverEye.skeletonData.findAnimation("eye-movement");
        SpineGameOverEye.animationEyeStationary = SpineGameOverEye.skeletonData.findAnimation("eye-stationary");
        SpineGameOverEye.animationNeedle00 = SpineGameOverEye.skeletonData.findAnimation("needle00");
        SpineGameOverEye.animationNeedle01 = SpineGameOverEye.skeletonData.findAnimation("needle01");
        SpineGameOverEye.animationNeedle02 = SpineGameOverEye.skeletonData.findAnimation("needle02");
        SpineGameOverEye.animationNeedle03 = SpineGameOverEye.skeletonData.findAnimation("needle03");
        SpineGameOverEye.animationNeedle04 = SpineGameOverEye.skeletonData.findAnimation("needle04");
        SpineGameOverEye.eventBgmGameover4 = SpineGameOverEye.skeletonData.findEvent("bgm/gameover4");
        SpineGameOverEye.eventBgmGameoverscream = SpineGameOverEye.skeletonData.findEvent("bgm/gameoverscream");
        SpineGameOverEye.boneRoot = SpineGameOverEye.skeletonData.findBone("root");
        SpineGameOverEye.boneGameGameOverSyringe = SpineGameOverEye.skeletonData.findBone("game/game-over-syringe");
        SpineGameOverEye.boneGameGameOverEye = SpineGameOverEye.skeletonData.findBone("game/game-over-eye");
        SpineGameOverEye.slotGameGameOverEyeBackground = SpineGameOverEye.skeletonData.findSlot("game/game-over-eye-background");
        SpineGameOverEye.slotGameGameOverEye = SpineGameOverEye.skeletonData.findSlot("game/game-over-eye");
        SpineGameOverEye.slotGameGameOverEyeForeground = SpineGameOverEye.skeletonData.findSlot("game/game-over-eye-foreground");
        SpineGameOverEye.slotGameGameOverClip = SpineGameOverEye.skeletonData.findSlot("game/game-over-clip");
        SpineGameOverEye.slotGameGameOverClip4 = SpineGameOverEye.skeletonData.findSlot("game/game-over-clip4");
        SpineGameOverEye.slotGameGameOverClip2 = SpineGameOverEye.skeletonData.findSlot("game/game-over-clip2");
        SpineGameOverEye.slotGameGameOverClip3 = SpineGameOverEye.skeletonData.findSlot("game/game-over-clip3");
        SpineGameOverEye.slotClip = SpineGameOverEye.skeletonData.findSlot("clip");
        SpineGameOverEye.slotGameGameOverSyringe = SpineGameOverEye.skeletonData.findSlot("game/game-over-syringe");
        SpineGameOverEye.skinDefault = SpineGameOverEye.skeletonData.findSkin("default");
        SpineGameOverHand.skeletonData = assetManager.get("spine/game-over-hand.json");
        SpineGameOverHand.animationData = assetManager.get("spine/game-over-hand.json-animation");
        SpineGameOverHand.animationGoToPosition = SpineGameOverHand.skeletonData.findAnimation("goToPosition");
        SpineGameOverHand.animationHurt00 = SpineGameOverHand.skeletonData.findAnimation("hurt00");
        SpineGameOverHand.animationHurt01 = SpineGameOverHand.skeletonData.findAnimation("hurt01");
        SpineGameOverHand.animationHurt02 = SpineGameOverHand.skeletonData.findAnimation("hurt02");
        SpineGameOverHand.animationHurt03 = SpineGameOverHand.skeletonData.findAnimation("hurt03");
        SpineGameOverHand.animationHurt04 = SpineGameOverHand.skeletonData.findAnimation("hurt04");
        SpineGameOverHand.animationPosition = SpineGameOverHand.skeletonData.findAnimation("position");
        SpineGameOverHand.animationSmack = SpineGameOverHand.skeletonData.findAnimation("smack");
        SpineGameOverHand.animationStand = SpineGameOverHand.skeletonData.findAnimation("stand");
        SpineGameOverHand.eventBgmGameover2 = SpineGameOverHand.skeletonData.findEvent("bgm/gameover2");
        SpineGameOverHand.eventBgmGameovermeat2 = SpineGameOverHand.skeletonData.findEvent("bgm/gameovermeat2");
        SpineGameOverHand.eventBgmGameoverscream2 = SpineGameOverHand.skeletonData.findEvent("bgm/gameoverscream2");
        SpineGameOverHand.boneRoot = SpineGameOverHand.skeletonData.findBone("root");
        SpineGameOverHand.boneHammer = SpineGameOverHand.skeletonData.findBone("hammer");
        SpineGameOverHand.bonePuddle = SpineGameOverHand.skeletonData.findBone("puddle");
        SpineGameOverHand.boneHand = SpineGameOverHand.skeletonData.findBone("hand");
        SpineGameOverHand.slotWhite = SpineGameOverHand.skeletonData.findSlot("white");
        SpineGameOverHand.slotPuddle = SpineGameOverHand.skeletonData.findSlot("puddle");
        SpineGameOverHand.slotWrist = SpineGameOverHand.skeletonData.findSlot("wrist");
        SpineGameOverHand.slotHand = SpineGameOverHand.skeletonData.findSlot("hand");
        SpineGameOverHand.slotHammer = SpineGameOverHand.skeletonData.findSlot("hammer");
        SpineGameOverHand.skinDefault = SpineGameOverHand.skeletonData.findSkin("default");
        SpineGameOverLeg.skeletonData = assetManager.get("spine/game-over-leg.json");
        SpineGameOverLeg.animationData = assetManager.get("spine/game-over-leg.json-animation");
        SpineGameOverLeg.animationAnimation00 = SpineGameOverLeg.skeletonData.findAnimation("animation00");
        SpineGameOverLeg.animationAnimation01 = SpineGameOverLeg.skeletonData.findAnimation("animation01");
        SpineGameOverLeg.animationAnimation02 = SpineGameOverLeg.skeletonData.findAnimation("animation02");
        SpineGameOverLeg.animationAnimation03 = SpineGameOverLeg.skeletonData.findAnimation("animation03");
        SpineGameOverLeg.animationAnimation04 = SpineGameOverLeg.skeletonData.findAnimation("animation04");
        SpineGameOverLeg.animationAnimation05 = SpineGameOverLeg.skeletonData.findAnimation("animation05");
        SpineGameOverLeg.animationAnimation06 = SpineGameOverLeg.skeletonData.findAnimation("animation06");
        SpineGameOverLeg.animationStand = SpineGameOverLeg.skeletonData.findAnimation("stand");
        SpineGameOverLeg.eventBgmGameover2 = SpineGameOverLeg.skeletonData.findEvent("bgm/gameover2");
        SpineGameOverLeg.eventBgmGameoversaw = SpineGameOverLeg.skeletonData.findEvent("bgm/gameoversaw");
        SpineGameOverLeg.eventBgmGameoverscream = SpineGameOverLeg.skeletonData.findEvent("bgm/gameoverscream");
        SpineGameOverLeg.boneRoot = SpineGameOverLeg.skeletonData.findBone("root");
        SpineGameOverLeg.boneThigh = SpineGameOverLeg.skeletonData.findBone("thigh");
        SpineGameOverLeg.boneUpperLeg = SpineGameOverLeg.skeletonData.findBone("upper-leg");
        SpineGameOverLeg.boneLowerLeg = SpineGameOverLeg.skeletonData.findBone("lower-leg");
        SpineGameOverLeg.boneSaw = SpineGameOverLeg.skeletonData.findBone("saw");
        SpineGameOverLeg.bonePuddle = SpineGameOverLeg.skeletonData.findBone("puddle");
        SpineGameOverLeg.slotWhite = SpineGameOverLeg.skeletonData.findSlot("white");
        SpineGameOverLeg.slotPuddle = SpineGameOverLeg.skeletonData.findSlot("puddle");
        SpineGameOverLeg.slotLowerLeg = SpineGameOverLeg.skeletonData.findSlot("lower-leg");
        SpineGameOverLeg.slotUpperLeg = SpineGameOverLeg.skeletonData.findSlot("upper-leg");
        SpineGameOverLeg.slotThigh = SpineGameOverLeg.skeletonData.findSlot("thigh");
        SpineGameOverLeg.slotSaw = SpineGameOverLeg.skeletonData.findSlot("saw");
        SpineGameOverLeg.skinDefault = SpineGameOverLeg.skeletonData.findSkin("default");
        SpineGameOverNoose.skeletonData = assetManager.get("spine/game-over-noose.json");
        SpineGameOverNoose.animationData = assetManager.get("spine/game-over-noose.json-animation");
        SpineGameOverNoose.animationDeath = SpineGameOverNoose.skeletonData.findAnimation("death");
        SpineGameOverNoose.animationWiggle = SpineGameOverNoose.skeletonData.findAnimation("wiggle");
        SpineGameOverNoose.eventBgmGameoverlever = SpineGameOverNoose.skeletonData.findEvent("bgm/gameoverlever");
        SpineGameOverNoose.boneRoot = SpineGameOverNoose.skeletonData.findBone("root");
        SpineGameOverNoose.boneRope = SpineGameOverNoose.skeletonData.findBone("rope");
        SpineGameOverNoose.boneFriend00 = SpineGameOverNoose.skeletonData.findBone("friend00");
        SpineGameOverNoose.boneFriend01 = SpineGameOverNoose.skeletonData.findBone("friend01");
        SpineGameOverNoose.boneFriend02 = SpineGameOverNoose.skeletonData.findBone("friend02");
        SpineGameOverNoose.boneFriend03 = SpineGameOverNoose.skeletonData.findBone("friend03");
        SpineGameOverNoose.boneFriend04 = SpineGameOverNoose.skeletonData.findBone("friend04");
        SpineGameOverNoose.boneFriend05 = SpineGameOverNoose.skeletonData.findBone("friend05");
        SpineGameOverNoose.slotWhite = SpineGameOverNoose.skeletonData.findSlot("white");
        SpineGameOverNoose.slotClip = SpineGameOverNoose.skeletonData.findSlot("clip");
        SpineGameOverNoose.slotRope = SpineGameOverNoose.skeletonData.findSlot("rope");
        SpineGameOverNoose.slotFriend = SpineGameOverNoose.skeletonData.findSlot("friend");
        SpineGameOverNoose.skinDefault = SpineGameOverNoose.skeletonData.findSkin("default");
        SpineGameOverShoot.skeletonData = assetManager.get("spine/game-over-shoot.json");
        SpineGameOverShoot.animationData = assetManager.get("spine/game-over-shoot.json-animation");
        SpineGameOverShoot.animationSlump = SpineGameOverShoot.skeletonData.findAnimation("slump");
        SpineGameOverShoot.animationWiggle = SpineGameOverShoot.skeletonData.findAnimation("wiggle");
        SpineGameOverShoot.eventBgmGameover3 = SpineGameOverShoot.skeletonData.findEvent("bgm/gameover3");
        SpineGameOverShoot.eventBgmGameovershot = SpineGameOverShoot.skeletonData.findEvent("bgm/gameovershot");
        SpineGameOverShoot.boneRoot = SpineGameOverShoot.skeletonData.findBone("root");
        SpineGameOverShoot.boneBone = SpineGameOverShoot.skeletonData.findBone("bone");
        SpineGameOverShoot.boneBone3 = SpineGameOverShoot.skeletonData.findBone("bone3");
        SpineGameOverShoot.boneBone4 = SpineGameOverShoot.skeletonData.findBone("bone4");
        SpineGameOverShoot.boneBone5 = SpineGameOverShoot.skeletonData.findBone("bone5");
        SpineGameOverShoot.boneBone6 = SpineGameOverShoot.skeletonData.findBone("bone6");
        SpineGameOverShoot.boneBone2 = SpineGameOverShoot.skeletonData.findBone("bone2");
        SpineGameOverShoot.boneGameGameOverBulletHole = SpineGameOverShoot.skeletonData.findBone("game/game-over-bullet-hole");
        SpineGameOverShoot.slotWhite = SpineGameOverShoot.skeletonData.findSlot("white");
        SpineGameOverShoot.slotClip = SpineGameOverShoot.skeletonData.findSlot("clip");
        SpineGameOverShoot.slotStreak = SpineGameOverShoot.skeletonData.findSlot("streak");
        SpineGameOverShoot.slotFriend = SpineGameOverShoot.skeletonData.findSlot("friend");
        SpineGameOverShoot.slotGameGameOverBulletHole = SpineGameOverShoot.skeletonData.findSlot("game/game-over-bullet-hole");
        SpineGameOverShoot.slotGameGameOverFlash = SpineGameOverShoot.skeletonData.findSlot("game/game-over-flash");
        SpineGameOverShoot.skinDefault = SpineGameOverShoot.skeletonData.findSkin("default");
        SpineGameOverTooth.skeletonData = assetManager.get("spine/game-over-tooth.json");
        SpineGameOverTooth.animationData = assetManager.get("spine/game-over-tooth.json-animation");
        SpineGameOverTooth.animationAnimation00 = SpineGameOverTooth.skeletonData.findAnimation("animation00");
        SpineGameOverTooth.animationAnimation01 = SpineGameOverTooth.skeletonData.findAnimation("animation01");
        SpineGameOverTooth.animationAnimation02 = SpineGameOverTooth.skeletonData.findAnimation("animation02");
        SpineGameOverTooth.animationAnimation03 = SpineGameOverTooth.skeletonData.findAnimation("animation03");
        SpineGameOverTooth.animationAnimation04 = SpineGameOverTooth.skeletonData.findAnimation("animation04");
        SpineGameOverTooth.animationStand = SpineGameOverTooth.skeletonData.findAnimation("stand");
        SpineGameOverTooth.eventBgmGameoverscream = SpineGameOverTooth.skeletonData.findEvent("bgm/gameoverscream");
        SpineGameOverTooth.boneRoot = SpineGameOverTooth.skeletonData.findBone("root");
        SpineGameOverTooth.boneGameGameOverTooth2 = SpineGameOverTooth.skeletonData.findBone("game/game-over-tooth2");
        SpineGameOverTooth.boneGameGameOverClamp = SpineGameOverTooth.skeletonData.findBone("game/game-over-clamp");
        SpineGameOverTooth.slotGameWhite = SpineGameOverTooth.skeletonData.findSlot("game/white");
        SpineGameOverTooth.slotGameGameOverTooth = SpineGameOverTooth.skeletonData.findSlot("game/game-over-tooth");
        SpineGameOverTooth.slotGameGameOverTooth2 = SpineGameOverTooth.skeletonData.findSlot("game/game-over-tooth2");
        SpineGameOverTooth.slotGameGameOverTooth3 = SpineGameOverTooth.skeletonData.findSlot("game/game-over-tooth3");
        SpineGameOverTooth.slotGameGameOverGum = SpineGameOverTooth.skeletonData.findSlot("game/game-over-gum");
        SpineGameOverTooth.slotGameGameOverClamp = SpineGameOverTooth.skeletonData.findSlot("game/game-over-clamp");
        SpineGameOverTooth.skinDefault = SpineGameOverTooth.skeletonData.findSkin("default");
        SpineLibgdx.skeletonData = assetManager.get("spine/libgdx.json");
        SpineLibgdx.animationData = assetManager.get("spine/libgdx.json-animation");
        SpineLibgdx.animationAnimation = SpineLibgdx.skeletonData.findAnimation("animation");
        SpineLibgdx.animationStand = SpineLibgdx.skeletonData.findAnimation("stand");
        SpineLibgdx.eventSfxLibgdx00 = SpineLibgdx.skeletonData.findEvent("sfx/libgdx00");
        SpineLibgdx.eventSfxLibgdx01 = SpineLibgdx.skeletonData.findEvent("sfx/libgdx01");
        SpineLibgdx.eventSfxLibgdx02 = SpineLibgdx.skeletonData.findEvent("sfx/libgdx02");
        SpineLibgdx.boneRoot = SpineLibgdx.skeletonData.findBone("root");
        SpineLibgdx.boneLogoLibgdx013 = SpineLibgdx.skeletonData.findBone("logo/libgdx013");
        SpineLibgdx.slotLogoWhite = SpineLibgdx.skeletonData.findSlot("logo/white");
        SpineLibgdx.slotLogoLibgdx00 = SpineLibgdx.skeletonData.findSlot("logo/libgdx00");
        SpineLibgdx.slotLogoLibgdx01 = SpineLibgdx.skeletonData.findSlot("logo/libgdx01");
        SpineLibgdx.slotLogoLibgdx02 = SpineLibgdx.skeletonData.findSlot("logo/libgdx02");
        SpineLibgdx.slotLogoLibgdx03 = SpineLibgdx.skeletonData.findSlot("logo/libgdx03");
        SpineLibgdx.slotL = SpineLibgdx.skeletonData.findSlot("l");
        SpineLibgdx.slotI = SpineLibgdx.skeletonData.findSlot("i");
        SpineLibgdx.slotB = SpineLibgdx.skeletonData.findSlot("b");
        SpineLibgdx.slotG = SpineLibgdx.skeletonData.findSlot("g");
        SpineLibgdx.slotD = SpineLibgdx.skeletonData.findSlot("d");
        SpineLibgdx.slotX = SpineLibgdx.skeletonData.findSlot("x");
        SpineLibgdx.slotLogoLibgdx010 = SpineLibgdx.skeletonData.findSlot("logo/libgdx010");
        SpineLibgdx.slotLogoLibgdx011 = SpineLibgdx.skeletonData.findSlot("logo/libgdx011");
        SpineLibgdx.slotLogoLibgdx012 = SpineLibgdx.skeletonData.findSlot("logo/libgdx012");
        SpineLibgdx.slotLogoLibgdx013 = SpineLibgdx.skeletonData.findSlot("logo/libgdx013");
        SpineLibgdx.slotBlood = SpineLibgdx.skeletonData.findSlot("blood");
        SpineLibgdx.slotLogoLibgdxExplosion01 = SpineLibgdx.skeletonData.findSlot("logo/libgdx_explosion-01");
        SpineLibgdx.skinDefault = SpineLibgdx.skeletonData.findSkin("default");
        SpineRay3k.skeletonData = assetManager.get("spine/ray3k.json");
        SpineRay3k.animationData = assetManager.get("spine/ray3k.json-animation");
        SpineRay3k.animationAnimation = SpineRay3k.skeletonData.findAnimation("animation");
        SpineRay3k.animationStand = SpineRay3k.skeletonData.findAnimation("stand");
        SpineRay3k.eventSfxRaeleus00 = SpineRay3k.skeletonData.findEvent("sfx/raeleus00");
        SpineRay3k.eventSfxRaeleus01 = SpineRay3k.skeletonData.findEvent("sfx/raeleus01");
        SpineRay3k.boneRoot = SpineRay3k.skeletonData.findBone("root");
        SpineRay3k.boneLogoBlank01 = SpineRay3k.skeletonData.findBone("logo-blank01");
        SpineRay3k.boneLogoBlank02 = SpineRay3k.skeletonData.findBone("logo-blank02");
        SpineRay3k.slotWhite = SpineRay3k.skeletonData.findSlot("white");
        SpineRay3k.slotLogoBlank01 = SpineRay3k.skeletonData.findSlot("logo-blank01");
        SpineRay3k.slotLogoBlank02 = SpineRay3k.skeletonData.findSlot("logo-blank02");
        SpineRay3k.slotLogo = SpineRay3k.skeletonData.findSlot("logo");
        SpineRay3k.slotThrizzle = SpineRay3k.skeletonData.findSlot("thrizzle");
        SpineRay3k.slotGamers = SpineRay3k.skeletonData.findSlot("gamers");
        SpineRay3k.slotAmerican = SpineRay3k.skeletonData.findSlot("american");
        SpineRay3k.skinDefault = SpineRay3k.skeletonData.findSkin("default");
        SpineSantaHeadOld.skeletonData = assetManager.get("spine/santa-head-old.json");
        SpineSantaHeadOld.animationData = assetManager.get("spine/santa-head-old.json-animation");
        SpineSantaHeadOld.animationAnimation = SpineSantaHeadOld.skeletonData.findAnimation("animation");
        SpineSantaHeadOld.eventSfxChapter1 = SpineSantaHeadOld.skeletonData.findEvent("sfx/chapter1");
        SpineSantaHeadOld.eventSfxChapter2 = SpineSantaHeadOld.skeletonData.findEvent("sfx/chapter2");
        SpineSantaHeadOld.eventSfxChapter3 = SpineSantaHeadOld.skeletonData.findEvent("sfx/chapter3");
        SpineSantaHeadOld.eventSfxChapter4 = SpineSantaHeadOld.skeletonData.findEvent("sfx/chapter4");
        SpineSantaHeadOld.eventSfxConclusion = SpineSantaHeadOld.skeletonData.findEvent("sfx/conclusion");
        SpineSantaHeadOld.eventSfxGameover1 = SpineSantaHeadOld.skeletonData.findEvent("sfx/gameover1");
        SpineSantaHeadOld.eventSfxGameover2 = SpineSantaHeadOld.skeletonData.findEvent("sfx/gameover2");
        SpineSantaHeadOld.eventSfxGameover3 = SpineSantaHeadOld.skeletonData.findEvent("sfx/gameover3");
        SpineSantaHeadOld.eventSfxGameover4 = SpineSantaHeadOld.skeletonData.findEvent("sfx/gameover4");
        SpineSantaHeadOld.eventSfxGameover5 = SpineSantaHeadOld.skeletonData.findEvent("sfx/gameover5");
        SpineSantaHeadOld.eventSfxGameover6 = SpineSantaHeadOld.skeletonData.findEvent("sfx/gameover6");
        SpineSantaHeadOld.boneRoot = SpineSantaHeadOld.skeletonData.findBone("root");
        SpineSantaHeadOld.slotWhite = SpineSantaHeadOld.skeletonData.findSlot("white");
        SpineSantaHeadOld.slotHead = SpineSantaHeadOld.skeletonData.findSlot("head");
        SpineSantaHeadOld.slotMouth = SpineSantaHeadOld.skeletonData.findSlot("mouth");
        SpineSantaHeadOld.skinDefault = SpineSantaHeadOld.skeletonData.findSkin("default");
        SpineSantaHead.skeletonData = assetManager.get("spine/santa-head.json");
        SpineSantaHead.animationData = assetManager.get("spine/santa-head.json-animation");
        SpineSantaHead.animationAnimation = SpineSantaHead.skeletonData.findAnimation("animation");
        SpineSantaHead.animationSaySfxChapter1 = SpineSantaHead.skeletonData.findAnimation("say_sfx/chapter1");
        SpineSantaHead.animationSaySfxChapter2 = SpineSantaHead.skeletonData.findAnimation("say_sfx/chapter2");
        SpineSantaHead.animationSaySfxChapter3 = SpineSantaHead.skeletonData.findAnimation("say_sfx/chapter3");
        SpineSantaHead.animationSaySfxChapter4 = SpineSantaHead.skeletonData.findAnimation("say_sfx/chapter4");
        SpineSantaHead.animationSaySfxConclusion = SpineSantaHead.skeletonData.findAnimation("say_sfx/conclusion");
        SpineSantaHead.eventSfxChapter1 = SpineSantaHead.skeletonData.findEvent("sfx/chapter1");
        SpineSantaHead.eventSfxChapter2 = SpineSantaHead.skeletonData.findEvent("sfx/chapter2");
        SpineSantaHead.eventSfxChapter3 = SpineSantaHead.skeletonData.findEvent("sfx/chapter3");
        SpineSantaHead.eventSfxChapter4 = SpineSantaHead.skeletonData.findEvent("sfx/chapter4");
        SpineSantaHead.eventSfxConclusion = SpineSantaHead.skeletonData.findEvent("sfx/conclusion");
        SpineSantaHead.boneRoot = SpineSantaHead.skeletonData.findBone("root");
        SpineSantaHead.boneHead = SpineSantaHead.skeletonData.findBone("head");
        SpineSantaHead.slotWhite = SpineSantaHead.skeletonData.findSlot("white");
        SpineSantaHead.slotHead = SpineSantaHead.skeletonData.findSlot("head");
        SpineSantaHead.slotMouth = SpineSantaHead.skeletonData.findSlot("mouth");
        SpineSantaHead.skinDefault = SpineSantaHead.skeletonData.findSkin("default");
        SpineSimon.skeletonData = assetManager.get("spine/simon.json");
        SpineSimon.animationData = assetManager.get("spine/simon.json-animation");
        SpineSimon.animationBottom = SpineSimon.skeletonData.findAnimation("bottom");
        SpineSimon.animationBottomQuick = SpineSimon.skeletonData.findAnimation("bottom-quick");
        SpineSimon.animationIntro = SpineSimon.skeletonData.findAnimation("intro");
        SpineSimon.animationLeft = SpineSimon.skeletonData.findAnimation("left");
        SpineSimon.animationLeftQuick = SpineSimon.skeletonData.findAnimation("left-quick");
        SpineSimon.animationRight = SpineSimon.skeletonData.findAnimation("right");
        SpineSimon.animationRightQuick = SpineSimon.skeletonData.findAnimation("right-quick");
        SpineSimon.animationStand = SpineSimon.skeletonData.findAnimation("stand");
        SpineSimon.animationTop = SpineSimon.skeletonData.findAnimation("top");
        SpineSimon.animationTopQuick = SpineSimon.skeletonData.findAnimation("top-quick");
        SpineSimon.eventSfxChime = SpineSimon.skeletonData.findEvent("sfx/chime");
        SpineSimon.eventSfxPiano = SpineSimon.skeletonData.findEvent("sfx/piano");
        SpineSimon.eventSfxString = SpineSimon.skeletonData.findEvent("sfx/string");
        SpineSimon.eventSfxViolin = SpineSimon.skeletonData.findEvent("sfx/violin");
        SpineSimon.boneRoot = SpineSimon.skeletonData.findBone("root");
        SpineSimon.slotGameSimonBackground = SpineSimon.skeletonData.findSlot("game/simon-background");
        SpineSimon.slotGameSimonBottom = SpineSimon.skeletonData.findSlot("game/simon-bottom");
        SpineSimon.slotGameSimonLeft = SpineSimon.skeletonData.findSlot("game/simon-left");
        SpineSimon.slotGameSimonRight = SpineSimon.skeletonData.findSlot("game/simon-right");
        SpineSimon.slotGameSimonTop = SpineSimon.skeletonData.findSlot("game/simon-top");
        SpineSimon.slotBboxRight = SpineSimon.skeletonData.findSlot("bbox-right");
        SpineSimon.slotBboxLeft = SpineSimon.skeletonData.findSlot("bbox-left");
        SpineSimon.slotBboxTop = SpineSimon.skeletonData.findSlot("bbox-top");
        SpineSimon.slotBboxBottom = SpineSimon.skeletonData.findSlot("bbox-bottom");
        SpineSimon.skinDefault = SpineSimon.skeletonData.findSkin("default");
        SpineThemedHorror.skeletonData = assetManager.get("spine/themed-horror.json");
        SpineThemedHorror.animationData = assetManager.get("spine/themed-horror.json-animation");
        SpineThemedHorror.animationAnimation = SpineThemedHorror.skeletonData.findAnimation("animation");
        SpineThemedHorror.animationStand = SpineThemedHorror.skeletonData.findAnimation("stand");
        SpineThemedHorror.eventSfxThemedHorror00 = SpineThemedHorror.skeletonData.findEvent("sfx/themed_horror00");
        SpineThemedHorror.eventSfxThemedHorror01 = SpineThemedHorror.skeletonData.findEvent("sfx/themed_horror01");
        SpineThemedHorror.eventSfxThemedHorror02 = SpineThemedHorror.skeletonData.findEvent("sfx/themed_horror02");
        SpineThemedHorror.boneRoot = SpineThemedHorror.skeletonData.findBone("root");
        SpineThemedHorror.boneBody = SpineThemedHorror.skeletonData.findBone("body");
        SpineThemedHorror.slotWhite = SpineThemedHorror.skeletonData.findSlot("white");
        SpineThemedHorror.slotBlack = SpineThemedHorror.skeletonData.findSlot("black");
        SpineThemedHorror.slotBody = SpineThemedHorror.skeletonData.findSlot("body");
        SpineThemedHorror.slotMouth = SpineThemedHorror.skeletonData.findSlot("mouth");
        SpineThemedHorror.skinDefault = SpineThemedHorror.skeletonData.findSkin("default");
        skin_skin = assetManager.get("skin/skin.json");
        SkinSkinStyles.lDefault = skin_skin.get("default", Label.LabelStyle.class);
        SkinSkinStyles.ttDefault = skin_skin.get("default", TextTooltip.TextTooltipStyle.class);
        sfx_chapter1 = assetManager.get("sfx/chapter1.mp3");
        sfx_chapter2 = assetManager.get("sfx/chapter2.mp3");
        sfx_chapter3 = assetManager.get("sfx/chapter3.mp3");
        sfx_chapter4 = assetManager.get("sfx/chapter4.mp3");
        sfx_chime = assetManager.get("sfx/chime.mp3");
        sfx_chord = assetManager.get("sfx/chord.mp3");
        sfx_clear = assetManager.get("sfx/clear.mp3");
        sfx_click = assetManager.get("sfx/click.mp3");
        sfx_conclusion = assetManager.get("sfx/conclusion.mp3");
        sfx_flagRemove = assetManager.get("sfx/flag remove.mp3");
        sfx_flag = assetManager.get("sfx/flag.mp3");
        sfx_highlight = assetManager.get("sfx/highlight.mp3");
        sfx_horn = assetManager.get("sfx/horn.mp3");
        sfx_libgdx00 = assetManager.get("sfx/libgdx00.mp3");
        sfx_libgdx01 = assetManager.get("sfx/libgdx01.mp3");
        sfx_libgdx02 = assetManager.get("sfx/libgdx02.mp3");
        sfx_piano = assetManager.get("sfx/piano.mp3");
        sfx_raeleus00 = assetManager.get("sfx/raeleus00.mp3");
        sfx_raeleus01 = assetManager.get("sfx/raeleus01.mp3");
        sfx_reset = assetManager.get("sfx/reset.mp3");
        sfx_string = assetManager.get("sfx/string.mp3");
        sfx_themedHorror00 = assetManager.get("sfx/themed_horror00.mp3");
        sfx_themedHorror01 = assetManager.get("sfx/themed_horror01.mp3");
        sfx_themedHorror02 = assetManager.get("sfx/themed_horror02.mp3");
        sfx_timerTick = assetManager.get("sfx/timer tick.mp3");
        sfx_violin = assetManager.get("sfx/violin.mp3");
        sfx_winSound = assetManager.get("sfx/win sound.mp3");
        bgm_audioSample = assetManager.get("bgm/audio-sample.mp3");
        bgm_disco = assetManager.get("bgm/disco.ogg");
        bgm_gameover1 = assetManager.get("bgm/gameover1.mp3");
        bgm_gameover2 = assetManager.get("bgm/gameover2.mp3");
        bgm_gameover3 = assetManager.get("bgm/gameover3.mp3");
        bgm_gameover4 = assetManager.get("bgm/gameover4.mp3");
        bgm_gameover5 = assetManager.get("bgm/gameover5.mp3");
        bgm_gameover6 = assetManager.get("bgm/gameover6.mp3");
        bgm_gameoverbreathing = assetManager.get("bgm/gameoverbreathing.mp3");
        bgm_gameovercry = assetManager.get("bgm/gameovercry.mp3");
        bgm_gameoverlever = assetManager.get("bgm/gameoverlever.mp3");
        bgm_gameovermeat = assetManager.get("bgm/gameovermeat.mp3");
        bgm_gameoversaw = assetManager.get("bgm/gameoversaw.mp3");
        bgm_gameoverscream = assetManager.get("bgm/gameoverscream.mp3");
        bgm_gameovershot = assetManager.get("bgm/gameovershot.mp3");
        bgm_menu = assetManager.get("bgm/menu.mp3");
    }

    public static class SpineBoy {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static BoneData boneBoy;

        public static BoneData boneBoy2;

        public static SlotData slotWhite;

        public static SlotData slotBoy;

        public static SlotData slotBoy2;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineDisco {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static BoneData boneGameDiscoBody2;

        public static BoneData boneGameDiscoBody;

        public static BoneData boneGameDiscoHead;

        public static BoneData boneGameDiscoLimb;

        public static BoneData boneGameDiscoLimb2;

        public static BoneData boneGameDiscoLimb4;

        public static BoneData boneGameDiscoLimb3;

        public static SlotData slotGameDiscoBall;

        public static SlotData slotGameDiscoLimb;

        public static SlotData slotGameDiscoLimb4;

        public static SlotData slotGameDiscoLimb3;

        public static SlotData slotGameDiscoLimb2;

        public static SlotData slotGameDiscoBody;

        public static SlotData slotGameDiscoHead;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineGameOverEye {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationEyeMovement;

        public static Animation animationEyeStationary;

        public static Animation animationNeedle00;

        public static Animation animationNeedle01;

        public static Animation animationNeedle02;

        public static Animation animationNeedle03;

        public static Animation animationNeedle04;

        public static EventData eventBgmGameover4;

        public static EventData eventBgmGameoverscream;

        public static BoneData boneRoot;

        public static BoneData boneGameGameOverSyringe;

        public static BoneData boneGameGameOverEye;

        public static SlotData slotGameGameOverEyeBackground;

        public static SlotData slotGameGameOverEye;

        public static SlotData slotGameGameOverEyeForeground;

        public static SlotData slotGameGameOverClip;

        public static SlotData slotGameGameOverClip4;

        public static SlotData slotGameGameOverClip2;

        public static SlotData slotGameGameOverClip3;

        public static SlotData slotClip;

        public static SlotData slotGameGameOverSyringe;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineGameOverHand {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationGoToPosition;

        public static Animation animationHurt00;

        public static Animation animationHurt01;

        public static Animation animationHurt02;

        public static Animation animationHurt03;

        public static Animation animationHurt04;

        public static Animation animationPosition;

        public static Animation animationSmack;

        public static Animation animationStand;

        public static EventData eventBgmGameover2;

        public static EventData eventBgmGameovermeat2;

        public static EventData eventBgmGameoverscream2;

        public static BoneData boneRoot;

        public static BoneData boneHammer;

        public static BoneData bonePuddle;

        public static BoneData boneHand;

        public static SlotData slotWhite;

        public static SlotData slotPuddle;

        public static SlotData slotWrist;

        public static SlotData slotHand;

        public static SlotData slotHammer;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineGameOverLeg {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation00;

        public static Animation animationAnimation01;

        public static Animation animationAnimation02;

        public static Animation animationAnimation03;

        public static Animation animationAnimation04;

        public static Animation animationAnimation05;

        public static Animation animationAnimation06;

        public static Animation animationStand;

        public static EventData eventBgmGameover2;

        public static EventData eventBgmGameoversaw;

        public static EventData eventBgmGameoverscream;

        public static BoneData boneRoot;

        public static BoneData boneThigh;

        public static BoneData boneUpperLeg;

        public static BoneData boneLowerLeg;

        public static BoneData boneSaw;

        public static BoneData bonePuddle;

        public static SlotData slotWhite;

        public static SlotData slotPuddle;

        public static SlotData slotLowerLeg;

        public static SlotData slotUpperLeg;

        public static SlotData slotThigh;

        public static SlotData slotSaw;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineGameOverNoose {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationDeath;

        public static Animation animationWiggle;

        public static EventData eventBgmGameoverlever;

        public static BoneData boneRoot;

        public static BoneData boneRope;

        public static BoneData boneFriend00;

        public static BoneData boneFriend01;

        public static BoneData boneFriend02;

        public static BoneData boneFriend03;

        public static BoneData boneFriend04;

        public static BoneData boneFriend05;

        public static SlotData slotWhite;

        public static SlotData slotClip;

        public static SlotData slotRope;

        public static SlotData slotFriend;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineGameOverShoot {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationSlump;

        public static Animation animationWiggle;

        public static EventData eventBgmGameover3;

        public static EventData eventBgmGameovershot;

        public static BoneData boneRoot;

        public static BoneData boneBone;

        public static BoneData boneBone3;

        public static BoneData boneBone4;

        public static BoneData boneBone5;

        public static BoneData boneBone6;

        public static BoneData boneBone2;

        public static BoneData boneGameGameOverBulletHole;

        public static SlotData slotWhite;

        public static SlotData slotClip;

        public static SlotData slotStreak;

        public static SlotData slotFriend;

        public static SlotData slotGameGameOverBulletHole;

        public static SlotData slotGameGameOverFlash;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineGameOverTooth {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation00;

        public static Animation animationAnimation01;

        public static Animation animationAnimation02;

        public static Animation animationAnimation03;

        public static Animation animationAnimation04;

        public static Animation animationStand;

        public static EventData eventBgmGameoverscream;

        public static BoneData boneRoot;

        public static BoneData boneGameGameOverTooth2;

        public static BoneData boneGameGameOverClamp;

        public static SlotData slotGameWhite;

        public static SlotData slotGameGameOverTooth;

        public static SlotData slotGameGameOverTooth2;

        public static SlotData slotGameGameOverTooth3;

        public static SlotData slotGameGameOverGum;

        public static SlotData slotGameGameOverClamp;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineLibgdx {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationStand;

        public static EventData eventSfxLibgdx00;

        public static EventData eventSfxLibgdx01;

        public static EventData eventSfxLibgdx02;

        public static BoneData boneRoot;

        public static BoneData boneLogoLibgdx013;

        public static SlotData slotLogoWhite;

        public static SlotData slotLogoLibgdx00;

        public static SlotData slotLogoLibgdx01;

        public static SlotData slotLogoLibgdx02;

        public static SlotData slotLogoLibgdx03;

        public static SlotData slotL;

        public static SlotData slotI;

        public static SlotData slotB;

        public static SlotData slotG;

        public static SlotData slotD;

        public static SlotData slotX;

        public static SlotData slotLogoLibgdx010;

        public static SlotData slotLogoLibgdx011;

        public static SlotData slotLogoLibgdx012;

        public static SlotData slotLogoLibgdx013;

        public static SlotData slotBlood;

        public static SlotData slotLogoLibgdxExplosion01;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineRay3k {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationStand;

        public static EventData eventSfxRaeleus00;

        public static EventData eventSfxRaeleus01;

        public static BoneData boneRoot;

        public static BoneData boneLogoBlank01;

        public static BoneData boneLogoBlank02;

        public static SlotData slotWhite;

        public static SlotData slotLogoBlank01;

        public static SlotData slotLogoBlank02;

        public static SlotData slotLogo;

        public static SlotData slotThrizzle;

        public static SlotData slotGamers;

        public static SlotData slotAmerican;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineSantaHeadOld {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static EventData eventSfxChapter1;

        public static EventData eventSfxChapter2;

        public static EventData eventSfxChapter3;

        public static EventData eventSfxChapter4;

        public static EventData eventSfxConclusion;

        public static EventData eventSfxGameover1;

        public static EventData eventSfxGameover2;

        public static EventData eventSfxGameover3;

        public static EventData eventSfxGameover4;

        public static EventData eventSfxGameover5;

        public static EventData eventSfxGameover6;

        public static BoneData boneRoot;

        public static SlotData slotWhite;

        public static SlotData slotHead;

        public static SlotData slotMouth;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineSantaHead {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationSaySfxChapter1;

        public static Animation animationSaySfxChapter2;

        public static Animation animationSaySfxChapter3;

        public static Animation animationSaySfxChapter4;

        public static Animation animationSaySfxConclusion;

        public static EventData eventSfxChapter1;

        public static EventData eventSfxChapter2;

        public static EventData eventSfxChapter3;

        public static EventData eventSfxChapter4;

        public static EventData eventSfxConclusion;

        public static BoneData boneRoot;

        public static BoneData boneHead;

        public static SlotData slotWhite;

        public static SlotData slotHead;

        public static SlotData slotMouth;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineSimon {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationBottom;

        public static Animation animationBottomQuick;

        public static Animation animationIntro;

        public static Animation animationLeft;

        public static Animation animationLeftQuick;

        public static Animation animationRight;

        public static Animation animationRightQuick;

        public static Animation animationStand;

        public static Animation animationTop;

        public static Animation animationTopQuick;

        public static EventData eventSfxChime;

        public static EventData eventSfxPiano;

        public static EventData eventSfxString;

        public static EventData eventSfxViolin;

        public static BoneData boneRoot;

        public static SlotData slotGameSimonBackground;

        public static SlotData slotGameSimonBottom;

        public static SlotData slotGameSimonLeft;

        public static SlotData slotGameSimonRight;

        public static SlotData slotGameSimonTop;

        public static SlotData slotBboxRight;

        public static SlotData slotBboxLeft;

        public static SlotData slotBboxTop;

        public static SlotData slotBboxBottom;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineThemedHorror {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationStand;

        public static EventData eventSfxThemedHorror00;

        public static EventData eventSfxThemedHorror01;

        public static EventData eventSfxThemedHorror02;

        public static BoneData boneRoot;

        public static BoneData boneBody;

        public static SlotData slotWhite;

        public static SlotData slotBlack;

        public static SlotData slotBody;

        public static SlotData slotMouth;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SkinSkinStyles {
        public static Label.LabelStyle lDefault;

        public static TextTooltip.TextTooltipStyle ttDefault;
    }

    public static class Values {
        public static float jumpVelocity = 10.0f;

        public static String name = "Raeleus";

        public static boolean godMode = true;

        public static int id = 10;
    }
}
