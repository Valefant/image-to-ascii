import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import kotlin.math.abs

/**
 * Map containing gradations to the respecting gray value of the pixel for the ascii image.
 * As RGB(0, 0, 0) is black it will be covered by a character which takes up a lot of space.
 */
val grayLevelToAscii = linkedMapOf(
    0 to 'B',
    25 to 'R',
    50 to 'K',
    75 to 'P',
    100 to 'L',
    125 to '[',
    150 to '|',
    175 to ';',
    200 to ',',
    230 to '.',
    255 to ' '
)

fun main(args: Array<String>) {
    if (args.size < 2) {
        println("Usage: image-to-ascii.jar input.jpg output.txt")
        return
    }

    val (nameOfImage, nameOfOutput) = args
    val file = File(nameOfImage)
    val bufferedImage = try {
        ImageIO.read(file)
    } catch (e: IOException) {
        println("Image could not be found!")
        return
    }
    val grayValues2D = mutableListOf<List<Int>>()

    for (y in 0 until bufferedImage.height) {
        val row = mutableListOf<Int>()
        for (x in 0 until bufferedImage.width) {
            val rgb = bufferedImage.getRGB(x, y)
            // shifting bits for obtaining the red, green and blue color from the pixel
            val r = rgb shr 16 and 0xFF
            val g = rgb shr 8 and 0xFF
            val b = rgb and 0xFF

            /**
             * To gray color an image all the RGB values have to be the same.
             * In our case we are not interested in setting the gray value but saving it.
             * Thus we can search for the fitting ascii character later.
             * A possible way to determine the gray value of a pixel is to add up the RGB values and dividing them by 3.
             */
            val grayValue = (r + g + b) / 3
            row.add(grayValue)
        }
        grayValues2D.add(row)
    }

    File(nameOfOutput).printWriter().use { out ->
        var rowBuilder = StringBuilder()
        grayValues2D.forEach { grayLevels ->
            grayLevels.forEach { grayLevel ->
                val fittingKey =
                    grayLevelToAscii
                        .keys
                        // mapping keys with the difference between key and grayLevel
                        .map { key -> Pair(key, abs(key - grayLevel)) }
                        // allows us to sort the results
                        .sortedBy { it.second }
                        // and therefore getting the closest gradation
                        .map { it.first }
                        .first()

                rowBuilder.append(grayLevelToAscii[fittingKey])
            }
            out.println(rowBuilder.toString())
            rowBuilder = StringBuilder()
        }
    }
}