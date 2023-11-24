package domainmodel;

public enum Team {
    EXCERCISER{
        @Override
        public String toString(){
            return "Motionist";
        }
    },
    JUNIOR{
        @Override
        public String toString() {
            return "Junior";
        }
    },
    SENIOR{
        @Override
        public String toString() {
            return "Senior";
        }
    }
}
