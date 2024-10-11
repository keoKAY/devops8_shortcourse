#!/bin/bash

# this will show case the work with differnet type of loop
echo "1. Using forloop of c-style"
for ((i = 0; i < 10; i++)); do
    echo "value of i is = $i "
done

echo "2. for look with range"
items=(1 2 3 4 5 )
for item in "${items[@]}"; do 
    echo "value of item is = $item"
done


echo "3. while loop"
i=0
while (( i < 5 )); do 
    echo "value of i is = $i"
    ((i++))
done

echo "4. until loop"
y=0
until (( y == 5 )); do 
    echo "value of y is = $y"
    ((y++))
done