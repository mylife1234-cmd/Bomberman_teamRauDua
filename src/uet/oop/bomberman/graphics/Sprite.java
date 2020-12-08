package uet.oop.bomberman.graphics;

import javafx.scene.image.*;

import java.awt.image.BufferedImage;
import java.nio.IntBuffer;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class Sprite {

	public static final int DEFAULT_SIZE = 16;
	public static final int SCALED_SIZE = DEFAULT_SIZE * 2;
	private static final int TRANSPARENT_COLOR = 0xffff00ff;
	public final int SIZE;
	private int _x, _y;
	public int[] _pixels;
	protected int _realWidth;
	protected int _realHeight;
	private SpriteSheet _sheet;

	public static Sprite brick_on_fire_1 = new Sprite(64, 3, 0, SpriteSheet.tiles,64,64);
	public static Sprite brick_on_fire_2 = new Sprite(64, 4, 0, SpriteSheet.tiles,64,64);
	public static Sprite brick_on_fire_3 = new Sprite(64, 5, 0, SpriteSheet.tiles,64,64);
	public static Sprite brick_on_fire_4 = new Sprite(64, 6, 0, SpriteSheet.tiles,64,64);
	public static Sprite brick_on_fire_5 = new Sprite(64, 7, 0, SpriteSheet.tiles,64,64);

	public static Sprite border_sprite = new Sprite(64, 0, 0, SpriteSheet.tiles,64,64);
	public static Sprite brick_sprite = new Sprite(64, 1, 0, SpriteSheet.tiles,64,64);
	public static Sprite floor_sprite = new Sprite(64, 2, 0, SpriteSheet.tiles,64,64);

	public static Sprite player_forward_1 = new Sprite(64, 0, 1, SpriteSheet.tiles,64,64);
	public static Sprite player_forward_2 = new Sprite(64, 1, 1, SpriteSheet.tiles,64,64);
	public static Sprite player_forward_3 = new Sprite(64, 2, 1, SpriteSheet.tiles,64,64);
	public static Sprite player_forward_4 = new Sprite(64, 3, 1, SpriteSheet.tiles,64,64);
	public static Sprite player_forward_5 = new Sprite(64, 4, 1, SpriteSheet.tiles,64,64);
	public static Sprite player_forward_6 = new Sprite(64, 5, 1, SpriteSheet.tiles,64,64);
	public static Sprite player_forward_7 = new Sprite(64, 6, 1, SpriteSheet.tiles,64,64);
	public static Sprite player_forward_8 = new Sprite(64, 7, 1, SpriteSheet.tiles,64,64);

	public static Sprite player_back_1 = new Sprite(64, 0, 2, SpriteSheet.tiles,64,64);
	public static Sprite player_back_2 = new Sprite(64, 1, 2, SpriteSheet.tiles,64,64);
	public static Sprite player_back_3 = new Sprite(64, 2, 2, SpriteSheet.tiles,64,64);
	public static Sprite player_back_4 = new Sprite(64, 3, 2, SpriteSheet.tiles,64,64);
	public static Sprite player_back_5 = new Sprite(64, 4, 2, SpriteSheet.tiles,64,64);
	public static Sprite player_back_6 = new Sprite(64, 5, 2, SpriteSheet.tiles,64,64);
	public static Sprite player_back_7 = new Sprite(64, 6, 2, SpriteSheet.tiles,64,64);
	public static Sprite player_back_8 = new Sprite(64, 7, 2, SpriteSheet.tiles,64,64);

	public static Sprite player_side_1 = new Sprite(64, 0, 3, SpriteSheet.tiles,64,64);
	public static Sprite player_side_2 = new Sprite(64, 1, 3, SpriteSheet.tiles,64,64);
	public static Sprite player_side_3 = new Sprite(64, 2, 3, SpriteSheet.tiles,64,64);
	public static Sprite player_side_4 = new Sprite(64, 3, 3, SpriteSheet.tiles,64,64);
	public static Sprite player_side_5 = new Sprite(64, 4, 3, SpriteSheet.tiles,64,64);
	public static Sprite player_side_6 = new Sprite(64, 5, 3, SpriteSheet.tiles,64,64);
	public static Sprite player_side_7 = new Sprite(64, 6, 3, SpriteSheet.tiles,64,64);
	public static Sprite player_side_8 = new Sprite(64, 7, 3, SpriteSheet.tiles,64,64);

	public static Sprite bomb_1 = new Sprite(64, 8, 1, SpriteSheet.tiles,64,64);
	public static Sprite bomb_2 = new Sprite(64, 8, 2, SpriteSheet.tiles,64,64);
	public static Sprite bomb_3 = new Sprite(64, 8, 3, SpriteSheet.tiles,64,64);

	public static Sprite flame_1 = new Sprite(64, 0, 4, SpriteSheet.tiles,64,64);
	public static Sprite flame_2 = new Sprite(64, 1, 4, SpriteSheet.tiles,64,64);
	public static Sprite flame_3 = new Sprite(64, 2, 4, SpriteSheet.tiles,64,64);
	public static Sprite flame_4 = new Sprite(64, 3, 4, SpriteSheet.tiles,64,64);
	public static Sprite flame_5 = new Sprite(64, 4, 4, SpriteSheet.tiles,64,64);

	public static Sprite creep_forward_1 = new Sprite(64, 0, 5, SpriteSheet.tiles,64,64);
	public static Sprite creep_forward_2 = new Sprite(64, 1, 5, SpriteSheet.tiles,64,64);
	public static Sprite creep_forward_3 = new Sprite(64, 2, 5, SpriteSheet.tiles,64,64);
	public static Sprite creep_forward_4 = new Sprite(64, 3, 5, SpriteSheet.tiles,64,64);
	public static Sprite creep_forward_5 = new Sprite(64, 4, 5, SpriteSheet.tiles,64,64);
	public static Sprite creep_forward_6 = new Sprite(64, 5, 5, SpriteSheet.tiles,64,64);

	public static Sprite creep_back_1 = new Sprite(64, 0, 6, SpriteSheet.tiles,64,64);
	public static Sprite creep_back_2 = new Sprite(64, 1, 6, SpriteSheet.tiles,64,64);
	public static Sprite creep_back_3 = new Sprite(64, 2, 6, SpriteSheet.tiles,64,64);
	public static Sprite creep_back_4 = new Sprite(64, 3, 6, SpriteSheet.tiles,64,64);
	public static Sprite creep_back_5 = new Sprite(64, 4, 6, SpriteSheet.tiles,64,64);
	public static Sprite creep_back_6 = new Sprite(64, 5, 6, SpriteSheet.tiles,64,64);

	public static Sprite creep_side_1 = new Sprite(64, 0, 7, SpriteSheet.tiles,64,64);
	public static Sprite creep_side_2 = new Sprite(64, 1, 7, SpriteSheet.tiles,64,64);
	public static Sprite creep_side_3 = new Sprite(64, 2, 7, SpriteSheet.tiles,64,64);
	public static Sprite creep_side_4 = new Sprite(64, 3, 7, SpriteSheet.tiles,64,64);
	public static Sprite creep_side_5 = new Sprite(64, 4, 7, SpriteSheet.tiles,64,64);
	public static Sprite creep_side_6 = new Sprite(64, 5, 7, SpriteSheet.tiles,64,64);
	public static Sprite creep_side_7 = new Sprite(64, 6, 7, SpriteSheet.tiles,64,64);
	public Sprite(int size, int x, int y, SpriteSheet sheet, int rw, int rh) {
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		_x = x * SIZE;
		_y = y * SIZE;
		_sheet = sheet;
		_realWidth = rw;
		_realHeight = rh;
		load();
	}

	public Sprite(int size, int color) {
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	private void setColor(int color) {
		for (int i = 0; i < _pixels.length; i++) {
			_pixels[i] = color;
		}
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				_pixels[x + y * SIZE] = _sheet._pixels[(x + _x) + (y + _y) * _sheet.SIZE];
			}
		}
	}

	public static Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, int animate, int time) {
		int calc = animate % time;
		int diff = time / 3;

		if(calc < diff) {
			return normal;
		}

		if(calc < diff * 2) {
			return x1;
		}

		return x2;
	}

	public static Sprite movingSprite(Sprite x1, Sprite x2, int animate, int time) {
		int diff = time / 2;
		return (animate % time > diff) ? x1 : x2;
	}

	public int getSize() {
		return SIZE;
	}

	public int getPixel(int i) {
		return _pixels[i];
	}

	public Image getFxImage() {
		WritableImage wr = new WritableImage(SIZE, SIZE);
		PixelWriter pw = wr.getPixelWriter();
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				if ( _pixels[x + y * SIZE] == TRANSPARENT_COLOR) {
					pw.setArgb(x, y, 0);
				}
				else {
					pw.setArgb(x, y, _pixels[x + y * SIZE]);
				}
			}
		}
		Image input = new ImageView(wr).getImage();
		return resample(input, SCALED_SIZE / DEFAULT_SIZE);
	}

	private Image resample(Image input, int scaleFactor) {
		final int W = (int) input.getWidth();
		final int H = (int) input.getHeight();
		final int S = scaleFactor;

		WritableImage output = new WritableImage(
				W * S,
				H * S
		);

		PixelReader reader = input.getPixelReader();
		PixelWriter writer = output.getPixelWriter();

		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				final int argb = reader.getArgb(x, y);
				for (int dy = 0; dy < S; dy++) {
					for (int dx = 0; dx < S; dx++) {
						writer.setArgb(x * S + dx, y * S + dy, argb);
					}
				}
			}
		}

		return output;
	}
}
