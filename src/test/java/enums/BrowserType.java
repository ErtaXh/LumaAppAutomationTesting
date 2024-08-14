package enums;


    public enum BrowserType {
        CHROME("chrome"),
        FIREFOX("firefox"),
        SAFARI("safari");

        private final String name;

        BrowserType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

