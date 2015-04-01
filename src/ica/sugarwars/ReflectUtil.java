package ica.sugarwars;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Sijmen
 */
public class ReflectUtil {

    /**
     * Craetes an instance of a class that has at least 1 constructor that has 1 argument containing SugarWars.
     * For example if you give this method DropGun.class, it will return a new instance of this class.
     * If the given class does not contain a constructor with SugarWars, it will return null.
     *
     * @param clazz The class where to create the instance of.
     * @param arg The SugarWars instance that should be passed to the new instance.
     * @return The new instance of this class.
     */
    public static Object createInstanceWhereArgIsSugarWars(Class<?> clazz, SugarWars arg){
        Constructor constructor = ReflectUtil.findSugarWarsConstructor(clazz);
        if(constructor == null)
            return null;
        try {
            return constructor.newInstance((Object[]) new Object[]{arg});
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            Log.e(SugarWars.class.getName(), "Could not create new instance of  " + clazz.getName() + " because error. " + e.getMessage(), e);
            return null;
        }
    }

    /**
     * Finds a constructor that takes 1 SugarWars argument.
     * @param clazz The class to search in
     * @return The found Constructor or null if nothing was found.
     */
    public static Constructor findSugarWarsConstructor(Class<?> clazz){
        for(Constructor c : clazz.getConstructors()){
            Class<?>[] parameters = c.getParameterTypes();
            if(parameters.length != 1)
                continue;
            if(!parameters[0].getName().equals(SugarWars.class.getName()))
                continue;
            return c;
        }
        Log.e(SugarWars.class.getName(), "Could not find fitting constructor at " + clazz.getName() + " .");
        return null;
    }

}
