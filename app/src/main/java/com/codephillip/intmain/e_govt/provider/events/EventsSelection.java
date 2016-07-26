package com.codephillip.intmain.e_govt.provider.events;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.codephillip.intmain.e_govt.provider.base.AbstractSelection;

/**
 * Selection for the {@code events} table.
 */
public class EventsSelection extends AbstractSelection<EventsSelection> {
    @Override
    protected Uri baseUri() {
        return EventsColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code EventsCursor} object, which is positioned before the first entry, or null.
     */
    public EventsCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new EventsCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public EventsCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public EventsCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public EventsSelection id(long... value) {
        addEquals("events." + EventsColumns._ID, toObjectArray(value));
        return this;
    }

    public EventsSelection idEvents(Integer... value) {
        addEquals(EventsColumns.ID_EVENTS, value);
        return this;
    }

    public EventsSelection idEventsNot(Integer... value) {
        addNotEquals(EventsColumns.ID_EVENTS, value);
        return this;
    }

    public EventsSelection idEventsGt(int value) {
        addGreaterThan(EventsColumns.ID_EVENTS, value);
        return this;
    }

    public EventsSelection idEventsGtEq(int value) {
        addGreaterThanOrEquals(EventsColumns.ID_EVENTS, value);
        return this;
    }

    public EventsSelection idEventsLt(int value) {
        addLessThan(EventsColumns.ID_EVENTS, value);
        return this;
    }

    public EventsSelection idEventsLtEq(int value) {
        addLessThanOrEquals(EventsColumns.ID_EVENTS, value);
        return this;
    }

    public EventsSelection date(String... value) {
        addEquals(EventsColumns.DATE, value);
        return this;
    }

    public EventsSelection dateNot(String... value) {
        addNotEquals(EventsColumns.DATE, value);
        return this;
    }

    public EventsSelection dateLike(String... value) {
        addLike(EventsColumns.DATE, value);
        return this;
    }

    public EventsSelection dateContains(String... value) {
        addContains(EventsColumns.DATE, value);
        return this;
    }

    public EventsSelection dateStartsWith(String... value) {
        addStartsWith(EventsColumns.DATE, value);
        return this;
    }

    public EventsSelection dateEndsWith(String... value) {
        addEndsWith(EventsColumns.DATE, value);
        return this;
    }

    public EventsSelection title(String... value) {
        addEquals(EventsColumns.TITLE, value);
        return this;
    }

    public EventsSelection titleNot(String... value) {
        addNotEquals(EventsColumns.TITLE, value);
        return this;
    }

    public EventsSelection titleLike(String... value) {
        addLike(EventsColumns.TITLE, value);
        return this;
    }

    public EventsSelection titleContains(String... value) {
        addContains(EventsColumns.TITLE, value);
        return this;
    }

    public EventsSelection titleStartsWith(String... value) {
        addStartsWith(EventsColumns.TITLE, value);
        return this;
    }

    public EventsSelection titleEndsWith(String... value) {
        addEndsWith(EventsColumns.TITLE, value);
        return this;
    }

    public EventsSelection story(String... value) {
        addEquals(EventsColumns.STORY, value);
        return this;
    }

    public EventsSelection storyNot(String... value) {
        addNotEquals(EventsColumns.STORY, value);
        return this;
    }

    public EventsSelection storyLike(String... value) {
        addLike(EventsColumns.STORY, value);
        return this;
    }

    public EventsSelection storyContains(String... value) {
        addContains(EventsColumns.STORY, value);
        return this;
    }

    public EventsSelection storyStartsWith(String... value) {
        addStartsWith(EventsColumns.STORY, value);
        return this;
    }

    public EventsSelection storyEndsWith(String... value) {
        addEndsWith(EventsColumns.STORY, value);
        return this;
    }

    public EventsSelection image(String... value) {
        addEquals(EventsColumns.IMAGE, value);
        return this;
    }

    public EventsSelection imageNot(String... value) {
        addNotEquals(EventsColumns.IMAGE, value);
        return this;
    }

    public EventsSelection imageLike(String... value) {
        addLike(EventsColumns.IMAGE, value);
        return this;
    }

    public EventsSelection imageContains(String... value) {
        addContains(EventsColumns.IMAGE, value);
        return this;
    }

    public EventsSelection imageStartsWith(String... value) {
        addStartsWith(EventsColumns.IMAGE, value);
        return this;
    }

    public EventsSelection imageEndsWith(String... value) {
        addEndsWith(EventsColumns.IMAGE, value);
        return this;
    }

    public EventsSelection ministry(String... value) {
        addEquals(EventsColumns.MINISTRY, value);
        return this;
    }

    public EventsSelection ministryNot(String... value) {
        addNotEquals(EventsColumns.MINISTRY, value);
        return this;
    }

    public EventsSelection ministryLike(String... value) {
        addLike(EventsColumns.MINISTRY, value);
        return this;
    }

    public EventsSelection ministryContains(String... value) {
        addContains(EventsColumns.MINISTRY, value);
        return this;
    }

    public EventsSelection ministryStartsWith(String... value) {
        addStartsWith(EventsColumns.MINISTRY, value);
        return this;
    }

    public EventsSelection ministryEndsWith(String... value) {
        addEndsWith(EventsColumns.MINISTRY, value);
        return this;
    }

    public EventsSelection location(String... value) {
        addEquals(EventsColumns.LOCATION, value);
        return this;
    }

    public EventsSelection locationNot(String... value) {
        addNotEquals(EventsColumns.LOCATION, value);
        return this;
    }

    public EventsSelection locationLike(String... value) {
        addLike(EventsColumns.LOCATION, value);
        return this;
    }

    public EventsSelection locationContains(String... value) {
        addContains(EventsColumns.LOCATION, value);
        return this;
    }

    public EventsSelection locationStartsWith(String... value) {
        addStartsWith(EventsColumns.LOCATION, value);
        return this;
    }

    public EventsSelection locationEndsWith(String... value) {
        addEndsWith(EventsColumns.LOCATION, value);
        return this;
    }
}
