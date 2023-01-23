# Quests

Versions: Made on 1.18 but will also work on 1.19
A spigot plugin that lets you create quests for player to complete throughout the day.\
use /quest to open the quest menu
## Permisions

By default each player gets 5 quests. To give more quest you give them the Quests.extra_# permission. For example if you want someone to have 3 extra quests you would give them Quests.extra3 and they will have 8 total quests unlocked for each week. The default would be no permission and the maximum is 7 for a total of 7 quests.

## Creating quests
After the plugin is ran on the server in the Quest plugin folder you will find the quests.yml file where you can create your own quests. Follow the below format:

The different types of quests can be Slayer, Move, Place, Break, Farm and Fishing. Depending on the type you will have to input a MobType or a BlockType see examples in the default yml file
```
Quests:
  '1':
    ID: 1
    Name: Grave Digger
    Description: Kill 100 Zombies
    Display_Item: ROTTEN_FLESH
    Amount: 100
    Reward: 2
    Type: Slayer
    MobType: ZOMBIE
    BlockType: 'null'
```

## Quest points
Quest points are awarded after completion of quest and can be spent in the quest shop. Below are the admin commands to edit players quest points.

/aquests reset {player}\
/aquests givepoints {player} {amount}\
/aquests removepoints {player} {amount}\
/aquests setpoints {player} {amount}

## Quest Shop
use /quest shop to open the shop\
*This is a work in progress the quest shop has not been impliment yet*


