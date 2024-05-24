package io.github.cvrunmin.createspawnerboxer;

import java.lang.reflect.Field;

import com.simibubi.create.content.kinetics.deployer.DeployerBlockEntity;

public class DeployerFieldCache {
    private static Field modeField;

    private static Object punchValue;
    private static Object useValue;

    private static boolean wasFailed = false;

    private static boolean shouldInitialize(){
        return !wasFailed && (modeField == null || punchValue == null || useValue == null);
    }

    private static void initializeReflection(){
        Field moField;
        try {
            moField = DeployerBlockEntity.class.getDeclaredField("mode");
            moField.setAccessible(true);
            DeployerFieldCache.modeField = moField;
            Object[] enumConstants = moField.getType().getEnumConstants();
            if(enumConstants == null){
                SpawnerBoxer.LOGGER.warn("cannot retrieve reflection information of DeployerBlockEntity$Mode");
                wasFailed = true;
                return;
            }
            for (Object object : enumConstants) {
                String enumName = ((Enum)object).name();
                if(enumName.equalsIgnoreCase("punch")){
                    punchValue = object;
                }
                else if(enumName.equalsIgnoreCase("use")){
                    useValue = object;
                }
            }
        } catch (NoSuchFieldException | SecurityException e) {
            SpawnerBoxer.LOGGER.warn("cannot retrieve reflection information of DeployerBlockEntity. Mod functions will be limited!", e);
            wasFailed = true;
        }
    }

    public static Object getMode(DeployerBlockEntity blockEntity){
        if (shouldInitialize()) {
            initializeReflection();
        }
        if(wasFailed){
            return null;
        }
        try {
            return modeField.get(blockEntity);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            SpawnerBoxer.LOGGER.warn("cannot get deployer mode via reflectiony. Mod functions will be limited!", e);
            return null;
        }
    }

    public static Object getPunchValue(){
        return punchValue;
    }

    public static Object getUseValue(){
        return useValue;
    }
}
