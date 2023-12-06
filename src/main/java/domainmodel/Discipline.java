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
            return "Brystsvømning";
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
