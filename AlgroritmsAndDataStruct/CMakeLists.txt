cmake_minimum_required(VERSION 3.23)
project(AlgroritmsAndDataStruct)

set(CMAKE_CXX_STANDARD 20)

add_executable(AlgroritmsAndDataStruct main.cpp)
add_executable(LeetCode LeetCodeTest.cpp)
add_executable(Prim "Prim's algorithm.cpp")
add_executable(FordFalkerson Ford-Falkerson.cpp)
