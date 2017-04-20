package controllers;

import models.Club;
import models.ClubList;
import play.mvc.*;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class ClubController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return clubs();
    }

    public Result clubs() {
        return ok(index.render(ClubList.getInstance().getClubList()));
    }
    public Result show(Long i) {
        return ok(clubview.render(ClubList.getInstance().findClub(i)));
    }

}
