if [[ $1 == "" ]] 
then
 echo "usage: $0 <jasmin_file.j>"
 exit 1
fi

java -jar jasmin.jar -d ../jasmin $1
