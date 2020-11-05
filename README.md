# test-assignment-task

Simple String Boot app implementing the assignment.

Inside the project's directory :

###### Build with ./gradlew build

###### Run tests with  ./gradlew test

###### Run the application. ./gradlew bootRun

Endpoints:

 */task* - returning the topologically sorted tasks as json array of tasks
 
 */task/bash* - returning topologically sorted tasks concatenated as a bash script

Both endpoints accept a json object with tasks as described by the assignment.

For topological sorting the Kahn's algorithm is implemented : see https://en.wikipedia.org/wiki/Topological_sorting
for more details