curl -i localhost:8080/jakarta-ee-demo-persistence/countries -X POST -H 'content-type: application/json' -d '{"isoCode":"CA" , "name":"Canada"                   , "phonePrefix":"1"  , "carCode":"CDN" , "population":"34482779"   , "continent":"NORTH_AMERICA"                    }';
curl -i localhost:8080/jakarta-ee-demo-persistence/countries -X POST -H 'content-type: application/json' -d '{"isoCode":"CN" , "name":"China"                    , "phonePrefix":"86" , "carCode":"RC"  , "population":"1331460000" , "continent":"ASIA"                             }';
curl -i localhost:8080/jakarta-ee-demo-persistence/countries -X POST -H 'content-type: application/json' -d '{"isoCode":"DE" , "name":"Germany"                  , "phonePrefix":"49" , "carCode":"D"   , "population":"81879976"   , "continent":"EUROPE"                           }';
curl -i localhost:8080/jakarta-ee-demo-persistence/countries -X POST -H 'content-type: application/json' -d '{"isoCode":"IT" , "name":"Italy"                    , "phonePrefix":"39" , "carCode":"I"   , "population":"60221210"   , "continent":"EUROPE"                           }';
curl -i localhost:8080/jakarta-ee-demo-persistence/countries -X POST -H 'content-type: application/json' -d '{"isoCode":"US" , "name":"United States of America" , "phonePrefix":"1"  , "carCode":"USA" , "population":"305548183"  , "continent":"NORTH_AMERICA"                    }';
curl -i localhost:8080/jakarta-ee-demo-persistence/countries -X POST -H 'content-type: application/json' -d '{"isoCode":"VN" , "name":"Vietnam"                  , "phonePrefix":"84" , "carCode":"VN"  , "population":"87840000"   , "continent":"ASIA"                             }';
curl -i localhost:8080/jakarta-ee-demo-persistence/countries -X POST -H 'content-type: application/json' -d '{"isoCode":"YU" , "name":"Yugoslavia"                                                                                  , "continent":"EUROPE"        , "expired":"true" }';