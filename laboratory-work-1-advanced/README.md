# Laboratory work 1 (Advanced)

## Variant

**Topic:** Mystical creatures (C3)

## Environment

To work with this project, you need this tools installed on your machine:
* Java 22+
* Maven

## Usage

Firstly, you must clone project from remote repository:

`git clone https://github.com/kol-oss/java-basics.git`

Then, move to the first laboratory work directory:

`cd laboratory-work-1-advaced`

Now you can run the example of usage by yourself in **edu.streams** package via **Main.java** class.

## Output

_Pay attention, that values are generated fully randomly and your output may be different._

After running main method in the Main class, you will get next output:

```shell
...

Total creatures: 500
Max power is: 99 [ANCIENT]
Min power is: 0 [ANCIENT]
Average power: 49.614
Standart deviation: 29.725008937132806
Interquartile range: 53.0
First quartile: 22.0
Third quartile: 75.0
Min outlier range: -57.5
Max outlier range: 154.5
Outliers Stats:
	ANCIENT: { data: 37, outliers: 0 }
	ICE: { data: 33, outliers: 0 }
	FAE: { data: 28, outliers: 0 }
	HYBRID: { data: 31, outliers: 0 }
	SHIFTER: { data: 31, outliers: 0 }
	DRAGON: { data: 26, outliers: 0 }
	CELESTIAL: { data: 36, outliers: 0 }
	DEMON: { data: 8, outliers: 0 }
	NATURE: { data: 31, outliers: 0 }
	LEGENDARY: { data: 37, outliers: 0 }
	SPIRIT: { data: 26, outliers: 0 }
	FIRE: { data: 30, outliers: 0 }
	AIR: { data: 25, outliers: 0 }
	WATER: { data: 32, outliers: 0 }
	EARTH: { data: 32, outliers: 0 }
	DESERT: { data: 27, outliers: 0 }
	TITAN: { data: 30, outliers: 0 }
```

You will get the output of all values and statistics, that are shown above. As you can see, if we use skip criteria based on DEMON creature type (check skipSelector in Main class), the demons amount (8) is less than average (about 30).