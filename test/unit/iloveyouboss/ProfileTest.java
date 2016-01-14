package unit.iloveyouboss;

import iloveyouboss.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by hzeng on 1/14/16.
 */
public class ProfileTest {

    private Question question;
    private Criteria criteria;
    private Profile profile;

    @Before
    public void setUp() throws Exception {
        question = new BooleanQuestion(1, "test");

        Answer answer = new Answer(question, 1);
        Criterion criterion = new Criterion(answer, Weight.Important);
        criteria = new Criteria();
        criteria.add(criterion);

        profile = new Profile("name");
    }

    @Test
    public void returns_true_when_answers_matched_given_one_answer() throws Exception {
        profile.add(new Answer(question, 1));

        boolean matches = profile.matches(criteria);

        assertThat(matches, is(true));
    }

    @Test
    public void returns_false_when_answers_not_matched_given_one_answer() throws Exception {
        profile.add(new Answer(question, 2));

        boolean matches = profile.matches(criteria);

        assertThat(matches, is(false));
    }
}