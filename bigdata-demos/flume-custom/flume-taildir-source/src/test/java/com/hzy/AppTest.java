package com.hzy;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.File;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    public static void main(String[] args) {
        File f = new File("xx/xxx/xxx/xx.txt");
        File availParentDir = null;
        while(availParentDir==null){
            File parent = f.getParentFile();
            if(parent.exists()){
                availParentDir = parent;
            }else{
                f = parent;
            }
        }
    }
}
