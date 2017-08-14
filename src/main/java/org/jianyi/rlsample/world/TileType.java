package org.jianyi.rlsample.world;

public enum TileType {
    E {
        @Override
        public int score() {
            return -3;
        }
    },
    P {
        @Override
        public int score() {
            return 5;
        }
    },
    C {
        @Override
        public int score() {
            return -1;
        }
    },
    D {
        @Override
        public int score() {
            return -10;
        }
    },
    W {
        @Override
        public int score() {
            return 0;
        }
    },
    G {
        @Override
        public int score() {
            return 0;
        }
    };

    public abstract int score();
}
