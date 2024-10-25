package com.company.oop.cosmetics;

import com.company.oop.cosmetics.core.CosmeticsEngineImpl;

public class Startup {

    public static void main(String[] args) {
        CosmeticsEngineImpl engine = new CosmeticsEngineImpl();
        engine.start();

    }

}
