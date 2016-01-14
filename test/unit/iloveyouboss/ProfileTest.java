package unit.iloveyouboss;

import iloveyouboss.*;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by hzeng on 1/14/16.
 */
public class ProfileTest {

    @Test
    public void returns_true_when_answers_matched_given_one_answer() throws Exception {
        Question question = new BooleanQuestion(1, "test");

        Answer answer = new Answer(question, 1);
        Criterion criterion = new Criterion(answer, Weight.Important);
        Criteria criteria = new Criteria();
        criteria.add(criterion);

        Profile profile = new Profile("name");
        Answer myAnswer = new Answer(question, 1);
        profile.add(myAnswer);

        boolean matches = profile.matches(criteria);

        assertThat(matches, is(true));
    }
}