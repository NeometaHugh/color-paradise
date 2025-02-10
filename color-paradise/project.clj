(defproject color-paradise "0.1.0-SNAPSHOT"
  :description "color-paradise a simple color game"
  :url "https://github.com/NeometaHugh/color-paradise.git"
  :user {:plugins [[lein-pom "1.1.1"]]
         :maven-repositories {"central" {:url "https://maven.aliyun.com/repository/central"}
                              "clojars" {:url "https://maven.aliyun.com/repository/clojars"}}
         :dependencies [[pom "1.1.1"]]}
  :license {:name "MIT"
            :url "https://mit-license.org/"}
  :dependencies [[org.clojure/clojure "1.11.1"]]
  :main ^:skip-aot color-paradise.core
  :repl-options {:init-ns color-paradise.core}
  :profiles {:uberjar {:aot :all}})
