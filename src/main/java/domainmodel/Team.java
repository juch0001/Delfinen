package domainmodel;

public enum Team {

    EXERCISER {
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
    },

     PENSIONER{
        @Override
      public String toString(){
          return "Pensionist";
      }
    }
}
