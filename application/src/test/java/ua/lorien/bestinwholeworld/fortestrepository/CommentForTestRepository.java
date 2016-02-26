package ua.lorien.bestinwholeworld.fortestrepository;

import java.util.Date;

import ua.lorien.bestinwholeworld.model.Comment;

public class CommentForTestRepository {

	public static Comment firstComment() {
		return new Comment("First comment. John Doe maid it", new Date());
	}

	public static Comment secondComment() {
		return new Comment("Second comment. John Doe again", new Date());
	}

	public static Comment thirdComment() {
		return new Comment("Third comment. Samanta Fox maid it", new Date());
	}

	public static Comment fourthComment() {
		return new Comment("Forth comment. Billy Kid maid it", new Date());
	}

	public static Comment fifthComment() {
		return new Comment("Fifth comment. Billy Kid again", new Date());
	}
}
