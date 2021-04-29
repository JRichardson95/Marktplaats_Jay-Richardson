package model;

public enum Bezorgwijze {
    AFHALEN_MAGAZIJN {
        @Override
        public String toString() {
            return ordinal() + ": Afhalen magazijn";
        }
    },
    THUIS_AFHALEN {
        @Override
        public String toString() {
            return ordinal()+ ": Thuis afhalen";
        }
    },
    VERSTUREN {
        @Override
        public String toString() {
            return ordinal()+ ": Versturen";
        }
    },
    VERSTUREN_ONDER_REMBOURS {
        @Override
        public String toString() {
            return ordinal()+ ": Versturen onder rembours";
        }
    }

}