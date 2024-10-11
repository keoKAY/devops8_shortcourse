

* Extract information 
* CSV files 
* logs file info 

```bash
cut -d" " -f2 cutsample.txt
cut -d:  -f1,3 /etc/passwd 
```

### Using cut with `tr` 
```bash
# replace @ with $
tr "@" "$" < cutsample.txt
cut -d" " -f2 

cat cutsample.txt | tr "@" "$" | cut -d" " -f2

# replace number to * 
tr '[0-9]' '*' < cutsample.txt
tr -d '[0-9]' < cutsample.txt
```

### Using file descriptor 

* `>>`: append 
* `>`: overwrite
```bash
echo -e "\n hello world " >> cutsample.txt
echo -e "\nhello world " > cutsample.txt

sudo apt update > /dev/null
date > /dev/null 
/dev/null # "black hole"

date 1>success.txt 2>error.txt
dating 1>success.txt 2>error.txt

# there are other two ways to do this 
date 1>logs.txt 2>&1
date &>logs.txt

```


## To read file 

```bash
head ,more , less , tail 

less # ctrl + z to exit 


head -2 /etc/passwd # first two line 
head -n 20 /etc/passwd # last 4 lines
```
* print from 17th to 20th 
```bash
awk 'NR>=17 && NR<=20 ' /etc/passwd
awk 'NR>=17 && NR<=20 {print NR, $1}' /etc/passwd
head -n 20 /etc/passwd | tail -n 4 | awk '{print NR, $1}'
```

```bash
cat /etc/passwd | grep "superman"
```