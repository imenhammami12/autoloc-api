
# Impact Faible
*Remove the 'ApiResponses' wrapper from this annotation group. [java:S1710]
*Remove this unused import 'org.springframework.transaction.annotation.Transactional'. [java:S1128]
*Remove this 'public' modifier. [java:S5786]
*Remove this empty class, write its code or make it an "interface". [java:S2094]
*Rename this generic name to match the regular expression '^[A-Z][0-9]?$'. [java:S119]
*Rename this bean to match the regular expression '^[a-z][a-zA-Z0-9]*$'. [java:S6830]
*Remove this unused "inutilise" private field. [java:S1068]
*Use isEmpty() to check whether the collection is empty or not. [java:S1155]
*Replace this if-then-else statement by a single return statement. [java:S1126]
*Remove this unused method parameter "niveau". [java:S1172]
*Remove this unused "inutile" local variable. [java:S1481]
*Complete the task associated to this TODO comment. [java:S1135]
*Remove this commented out code. [java:S125]
*Add a private constructor to hide the implicit public one. [java:S1118]

# Impact Moyen
*Replace this use of System.out by a logger. [java:S106]
*Add at least one assertion to this test case. [java:S2699]
*Add a nested comment explaining why this method is empty, throw an UnsupportedOperationException or complete the implementation. [java:S1186]
*Define a constant instead of duplicating this literal "Agence introuvable" 3 times. [java:S1192]

# Impact Important
*Replace this persistent entity with a simple POJO or DTO object. [java:S4684]
*Review this potentially hardcoded credential. [java:S2068]