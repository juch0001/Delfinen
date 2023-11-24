package domainmodel;

public enum Discipline {
    BACK_CRAWL {
        @Override
        public String toString(){
            return "Ryg crawl";
        }
    },
    BREASTSTROKE {
        @Override
        public String toString(){
            return "Bryst svømming";
        }
    },
    BUTTERFLY{
        @Override
        public String toString(){
            return "Butterfly";
        }
    },
    CRAWL {
        @Override
        public String toString() {
            return "Crawl";
        }
    }
}
