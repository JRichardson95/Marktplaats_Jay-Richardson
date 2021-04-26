package model;

public enum Bezorgwijze {
    AFHALEN_MAGAZIJN {
        @Override
        public String toString() {
            return "1: Afhalen magazijn";
        }
    },
    THUIS_AFHALEN {
        @Override
        public String toString() {
            return "2: Thuis afhalen";
        }
    },
    VERSTUREN {
        @Override
        public String toString() {
            return "3: Versturen";
        }
    },
    VERSTUREN_ONDER_REMBOURS {
        @Override
        public String toString() {
            return "4: Versturen onder rembours";
        }
    }

}