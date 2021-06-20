package repository;

import model.Comment;

public class CommentRepository extends GenericRepository<Comment, CommentRepository> {

	@Override
	protected String getFileName() {
		return "commentData.json";
	}

	@Override
	protected String getKey(Comment e) {
		return e.getId();
	}

}
