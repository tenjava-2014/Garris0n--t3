Garris0n-'s ten.java submission
==============================

[![ten.java](https://cdn.mediacru.sh/hu4CJqRD7AiB.svg)](https://tenjava.com/)

This is a submission for the 2014 ten.java contest.

- __Theme:__
- __Time:__ Time 3 (7/12/2014 14:00 to 7/13/2014 00:00 UTC)
- __MC Version:__ 1.7.9 (latest Bukkit beta)
- __Stream URL:__ None

<!-- put chosen theme above -->

---------------------------------------

Compilation
-----------

- Download & Install [Maven 3](http://maven.apache.org/download.html)
- Clone the repository: `git clone https://github.com/tenjava/Garris0n--t3`
- Compile and create the plugin package using Maven: `mvn`

Maven will download all required dependencies and build a ready-for-use plugin package!

---------------------------------------

Usage
---------------------------------------


1. Install plugin
2. Configure.
3. Enjoy giving people or mobs the plague :P

Configuration
-----

Data types:
String - This is the String from YAML.  Ex: 'hi there'
Int - The integer from YAML.  Ex: 1
Double - The double from YAML.  Ex: 1.328483
Location - A point in a certain world in the format 'world|x|y|z'. Ex: 'AWorld|100|50|100'

####Creating a plague
Here is an example plague:
```YAML
swine-flu:
  options: #This is where your basic plague settings go.
    display-name: 'Swine Flu' #The display name of this plague.
    enabled-worlds:  #The worlds in which this plague is enabled.
    - 'World'
    vulnerable-entity-types: #The EntityTypes this plague affects.  Only entities which are "alive" are allowed.
  attributes: #The attributes, as described below.
    potion:
      effect: WITHER
      duration: 200
      amplifier: 1
      ambient: false
      initial-delay: 0
      frequency: 20000
  causes:  #The causes, as described below.
    random:
      initial-delay: 0
      frequency: 20
```

[Here](http://jd.bukkit.org/dev/apidocs/org/bukkit/entity/EntityType.html) is a list of valid EntityTypes.

[Here](http://jd.bukkit.org/dev/apidocs/org/bukkit/potion/PotionEffectType.html) is a list of valid potion effects.

####Attributes:

Attributes are the things that allow your plague to, well...hurt things.  Each attribute has its own set of options.

#####Examples:

**Broadcast**
```YAML
broadcast:
  message: '&aThis is a green message.  Player name is %player-name%. Player display name is %player-display-name%.'  #The message to be broadcasted.
```

**Message**
```YAML
send-message:
  message: '&aThis is a green message.  Player name is %player-name%. Player display name is %player-display-name%.'  #The message to be sent to the player.
```

**Damage**
```YAML
damage:
  damage: 2  #How many half-hearts of damage.
  initial-delay: 0  #The delay (in ticks) after the entity is infected before starting.
  frequency: 20  #The delay before each subsequent damage application.  Set to -1 if you only want the initial damage (with the initial-delay).
```

**Potion Effect**
```YAML
potion:
  effect: REGENERATION #The potion effect.  Refer to the list of effects above.
  duration: 200  #The duration (in ticks) that the potion will last.
  amplifier: 50  #The amplifier of the potion.
  ambient: false  #Whether the potion should be ambient (ambient means the particles are transluscent).
  initial-delay: 0  #The delay (in ticks) after the entity is infected before the effect is applied.
  frequency: 10  #The delay (in ticks) before eqach subsequent application.  Set to -1 if you only want the initial effect (with the initial-delay).
```

####Causes:

Causes are the things that infect entities.

#####Examples:
**Block Under**
```YAML
block-under:
  chance: 100
  block: DIRT
```

**Contagion**
```YAML
contagion:
  chance: 100
  radius: 3

```

**Location Cuboid**
```YAML
location-cuboid:
  corner1: world|0|0|0
  corner2: world|1|1|1
```

**Location Radius**
```YAML
location-radius:
  center: world|0|0|0
  radius: 15.5
```

**Random**
```YAML
random:
  initial-delay: 5
  frequency: 10
```


