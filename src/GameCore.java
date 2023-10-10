import java.util.Scanner;

public class GameCore {

    Player player;
    Monster zombie;
    Monster slime;
    Monster wither;
    Shop shop;
    Inventory inventory;
    boolean dungeonConcluded = false;

    boolean playerIsDead = false;
    Dungeon dungeon;
    Dice dice;

    Scanner sc = new Scanner(System.in);


    public GameCore(String playerName) {
        player = new Player(playerName, this);
        zombie = new Monster("Zombie", 1200);
        slime = new Monster("Slime", 2100);
        wither = new Monster("Wither", 4900);
        dice = new Dice(player);
        shop = new Shop();
        inventory = new Inventory(player);
        dungeon = new Dungeon(player, this, shop);

    }

    public void start() {
        while(!playerIsDead) {
            System.out.println(" ");
            System.out.println("1 - Atacar Zombie [Fácil]");
            System.out.println("2 - Atacar Slime [Mediano]");
            System.out.println("3 - Atacar Wither [Dificíl]");
            System.out.println("4 - Modo Dungeon [Hardcore]");
            System.out.println("5 - Apostar (Par ou Ímpar)");
            System.out.println("6 - Status");
            System.out.println("7 - Inventário");
            System.out.println("8 - Loja");
            System.out.println("9 - Sair do Jogo");
            System.out.println(" ");
            System.out.print("Selecione o que fazer: ");


                switch (sc.nextInt()) {
                    case 1:
                        player.attack(zombie);
                        zombie.autoAttack(player);
                        break;
                    case 2:
                        player.attack(slime);
                        slime.autoAttack(player);
                        break;
                    case 3:
                        player.attack(wither);
                        wither.autoAttack(player);
                        break;
                    case 4:
                        if(!dungeonConcluded) {
                            dungeon.flyingisDead = false;
                            dungeon.endermanisDead = false;
                            dungeon.babyDragonisDead = false;
                            dungeon.giantisDead = false;
                            dungeon.dragonisDead = false;
                            dungeon.startDungeon();
                            break;
                        } else {
                            System.out.println("Dungeon já concluída.");
                            break;
                        }
                    case 5:
                        dice.playDice(player);
                        break;
                    case 6:
                        profile(player);
                        break;
                    case 7:
                        inventory.check(player);
                        break;
                    case 8:
                        System.out.println("Bem-vindo à Loja!" + " (Seu XP: " + player.getXp()+")" + " - (Suas moedas: " + player.coins + ")" + " - (Seu pó mágico: "+ player.magicPowder+")");
                        System.out.println("1 - Comprar XP Multiplier (100 XP)");
                        System.out.println("2 - Comprar VIDA (50 XP)");
                        System.out.println("3 - Comprar Armadura (150 XP)");
                        System.out.println("4 - Converter XP em Moedas (2 XP cada)");
                        System.out.println("5 - Comprar Espada de Esmeralda (75 Moedas)");
                        System.out.println("6 - Vender Espada de Esmeralda (60 Moedas)");
                        System.out.println("7 - Comprar pó mágico (17 Moedas cada)");
                        System.out.println("8 - Encantar espada (15 de pó mágico)");
                        System.out.println("9 - Sair da Loja");
                        System.out.print("Resposta: ");
                        menuShop();
                    case 9:
                        System.out.println("Você decidiu sair do jogo. O seu progresso não ficará guardado.");
                        playerIsDead = true;
                        dungeon.flyingisDead = true;
                        break;
                    case 111:
                        player.xp += 1000;
                        player.coins += 1000;
                        player.magicPowder += 1000;
                        System.out.println("Cheat usado!");
                        break;
                    default:
                        System.out.println("Número inválido.");
                        start();
                        break;
                }

            checkIfPlayerIsDead(player);
            }
        }

    public void menuShop() {
        switch (sc.nextInt()) {
            case 1:
                shop.buyXPMultiplier(player, 100);
                start();
                break;
            case 2:
                shop.buyLife(player, 50);
                start();
                break;
            case 3:
                shop.buyArmour(player, 200);
                start();
                break;
            case 4:
                shop.buyCoins(player);
                start();
                break;
            case 5:
                shop.buySword(player, 75);
                start();
                break;
            case 6:
                shop.sellSword(player);
                start();
                break;
            case 7:
                shop.buyMagicPowder(player, 17);
                start();
                break;
            case 8:
                shop.enchantSword(player);
                start();
                break;
            case 9:
                start();
                break;
            default:
                System.out.println("Número inválido.");
                start();
                break;
        }
    }

    public void profile(Player player) {
        System.out.println("❤\uFE0F " + player.hp + " (VIDA)");
        System.out.println("✨ " + player.xp + " (XP)");
        System.out.println("\uD83D\uDEE1\uFE0F " + player.armour + " % (ARMADURA)");
        System.out.println("\uD83E\uDD5E " + player.coins + " (MOEDAS)");
        System.out.println("");
        if(zombie.hp <= 0) {
            System.out.println("Zombie: ✅");
        } else {
            System.out.println("Zombie: ❌");
        }
        if(slime.hp <= 0) {
            System.out.println("Slime: ✅");
        } else {
            System.out.println("Slime: ❌");
        }
        if(wither.hp <= 0) {
            System.out.println("Wither: ✅");
        } else {
            System.out.println("Wither: ❌");
        }
        System.out.println("");
        if(player.xpMultiplier == 0) {
            System.out.println("XP Multiplier: ❌");
        } else {
            System.out.println("XP Multiplier: ✅");
        }
        if(player.damageMultiplier == 2.30) {
            System.out.println("Espada encantada: ❌");
        } else {
            System.out.println("Espada encantada: ✅");
        }
        if(!dungeonConcluded) {
            System.out.println("Dungeon concluída: ❌");
        } else {
            System.out.println("Dungeon concluída: ✅");
        }
        start();
    }

    public void checkIfPlayerIsDead(Player player) {
        if (player.hp <= 0) {
            playerIsDead = true;
        }
    }

    public void checkAttack(Monster monster) {
        System.out.println("Você atacou a " + monster.getName());
        if(monster.hp <= 0) {
            System.out.println("======================");
            System.out.println("Este monstro já está morto.");
            System.out.println("======================");
        } else {
            player.random();
            if(monster.hp <= 80) {
                System.out.println("======================");
                if(player.haveSword == true) {
                    System.out.println("Você usou a sua espada de esmeralda.");
                    System.out.printf("Tirou: %.2f", monster.hp*player.damageMultiplier);
                    System.out.println();
                    monster.setHp((int) (monster.hp-monster.hp*player.damageMultiplier));
                } else {
                    System.out.println("Tirou: " + monster.hp);
                }
                monster.setHp(monster.hp-monster.hp);
                System.out.println("Você matou o " + monster.name);
                if(monster.name == "Zombie") {
                    player.xp += 250;
                    player.coins += 125;
                    System.out.println("Você recebeu 250 de XP e 125 moedas por derrotar o Zombie.");
                } else if(monster.name == "Slime") {
                    player.xp += 500;
                    player.coins += 250;
                    System.out.println("Você recebeu 500 de XP e 250 moedas por derrotar a Slime.");
                } else if(monster.name == "Wither") {
                    player.xp += 700;
                    player.coins += 350;
                    System.out.println("Você recebeu 700 de XP e 350 moedas por derrotar o Wither.");
                }
                if(player.xpMultiplier > 2) {
                    player.xp += (float) (5 + player.xpMultiplier);
                    System.out.println("Ganhou: " + (5 * player.xpMultiplier) + " de XP.");
                    System.out.println("BUFF de XP: ATIVADO");
                } else {
                    System.out.println("Ganhou: " + 5 + " de xp.");
                    System.out.println("BUFF de XP: DESATIVADO");
                }
                System.out.println("======================");
            } else {
                player.xp += 5;
                System.out.println("======================");
                if(player.haveSword == true) {
                    System.out.println("Você usou a sua espada de esmeralda.");
                    System.out.printf("Tirou: %.2f", player.random*player.damageMultiplier);
                    System.out.println();
                    monster.setHp((int) (monster.hp-player.random*player.damageMultiplier));
                } else {
                    System.out.println("Tirou: " + player.random);
                    monster.setHp(monster.hp-player.random);
                }
                if(player.xpMultiplier > 2) {
                    player.xp += (float) (5 + player.xpMultiplier);
                    System.out.println("Ganhou: " + 5*player.xpMultiplier + " de XP.");
                    System.out.println("BUFF de XP: ATIVADO");
                } else {
                    System.out.println("Ganhou: " + 5 + " de xp.");
                    System.out.println("BUFF de XP: DESATIVADO");
                }
                System.out.println("Vida do " + monster.getName() + ": " + monster.hp);
                System.out.println("======================");
            }
        }
    }
}
