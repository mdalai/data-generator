

## Data Definitions

- hostname:  Get by running `hostname`
- ip:
- Software: 
   - OS_Distribution: 
   - OS_Version:
   - Kernel:
   - Architecture:
- Hardware:
   - cpu_product
   - cpu_speed
   - memory_capacity

## Generate code 
- create the openapi spec doc per data definitions.
- create python code per the spec doc.

```
cd 3p
wget https://repo1.maven.org/maven2/org/openapitools/openapi-generator-cli/4.3.1/openapi-generator-cli-4.3.1.jar -O openapi-generator-cli.jar
```

- create a config file in `configs/codegen.config` for openapi generator .

- How to generate code:

```
java -jar 3p/openapi-generator-cli.jar generate -g python -i metadata.yml -o . --global-property=models --global-property=modelTests=true -c configs/codegen.config
```

Generates followings:
- metadatapkg
- test
- docs

## Check Linux Software

- `lsb_release -d` or more details with `lsb_release -a`: **lsb_release** utility displays Linux Standard Base info about the Linux distribution.
- `/etc/os-release`: this file is part of systemd package.
- `/etc/issue`: this file contains system identification text displays before login prompt.
- `hostnamectl`: this utility is part of systemd package.
- `uname -srm`: returns the linux kernel and architecture.

## Check Linux Hardware
-`sudo lshw -short`: this utility provides a table of hardware overview.
- `sudo lshw -C cpu | grep -i product`: gives CPU make and model. For more info use `lshw -C cpu`. 
- `lscpu | grep -i mhz`: CPU's speed in megahertz. For more info use `lscpu`.  
- `lscpu | grep -i bogo`: BogoMips power rating. For more info use `lscpu`.  
- `sudo dmidecode -t memory | grep -i size`: memory stick and its capacity. For more details include type, size, speed and voltage of each RAM do `sudo lshw -short -C memory`.
- `sudo dmidecode -t memory | grep -i max`: maximum memory you can install.
- `sudo lshw -short -C memory | grep -i empty`: check if any open slots to insert additional memory sticks.
- `lspci | grep -i vga`:  get video controller device #,  then `lspci -v -s <device#>`: how much video memory you have.
- `free -m`: current memory usage.
- `sudo lshw -short -C disk`: disk size.
- `lsblk`: partitions along with size. `fdisk -l`, `df -m`, `lsusb`.
- `sudo lshw -C network`: hardware details of network.
- `sudo dmidecode -t bios`: shows UEFI or BIOS date and version.


**References**:
- https://opensource.com/article/19/9/linux-commands-hardware-information 