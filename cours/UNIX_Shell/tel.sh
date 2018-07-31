echo Entrez un numéro de téléphone
read -r var
if [ ${#var} -ge 10 ]
#if[[$var = [0-9] ]]
then
    echo good
else
    echo false
fi
if [ -n "$var" -a $var -eq "$var"] 2> /dev/null
then
    echo "yes"
fi

