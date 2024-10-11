#!/bin/bash
message="$1" 
if [ -z "$message" ]; then 
 echo "message cannot be empty !! 🔥" 
 exit 1 
# else 
#  echo "this message is not empty!"
fi 

echo "message is : $message"
# check if file empty or not 
if [[ -s "emptyfile.txt" ]];then 
 echo "this file is not empty!" 
else 
 echo "this file is empty!! "
fi 

# /dev/null 

if docker -v &>/dev/null; 
then 
echo "This is docker version: $(docker -v)" 
fi 

docker -v &>/dev/null 
if [[ $? -eq 0 ]]; then 
echo "This is docker version: $(docker -v)" 
fi
