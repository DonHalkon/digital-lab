# Digital-Lab #

Digital Lab is an util for people working in chemical laboratory. 
It contains tools for experiment planing, control of reactants on shelves, 
constants and formulas handbook, it also calculates reaction conditions and 
other variables. It stores all data about your experiment reactants, products, 
conditions, yelds, comments, photos, spectras and other data. And the main 
feature of this project: it has grpah database to connect your chemical 
reactions each other. You can share you data with other chemists from other 
labs in your or other organization and create your own knowlage base. It has 
convenient tools and templates for paper writing. We also have mobile app that 
you can work and correct you current experiment data without departing hood or 
weights.

But now it is only the plans for future)).

#  Installation #
## Build from source ##
1. Download [jsme](http://peter-ertl.com/jsme/)
2. Unzip 
3. Move jsme directory to src/main/resources/js/ 
4. Build:
```bash
gradle clean build
```
or using gradle wrapper
```bash
./gradlew clean build
```

## Run ##
### Run as jar file using h2 database: ###
```bash
java -jar digital-lab-0.0.1-SNAPSHOT.jar
```

### Run in docker using MySQL db (preferred): ###
```bash
gradle composeUp
```
or 
```bash
gradle buildDocker
cd docker
docker-compose up
```