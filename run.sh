cd bin

if [[ $1 == "" ]] 
then
 echo "usage: $0 <test_file.jmm>"
 exit 1
fi

java jmm ../$1
