package unit.iloveyouboss;

import iloveyouboss.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static iloveyouboss.Weight.DontCare;
import static iloveyouboss.Weight.MustMatch;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by hzeng on 1/14/16.
 */
public class ProfileTest {

    private Profile profile;
    private Question question;
    private Question question2;
    private Criterion criterionForQuestion1AndMustMatch;
    private Criterion criterionForQuestion2AndMustMatch;
    private Criterion criterionForQuestion1AndDontCare;
    private Criterion criterionForQuestion2AndDontCare;
    private Criteria criteria;

    @Before
    public void setUp() throws Exception {
        question = new BooleanQuestion(1, "test");
        question2 = new BooleanQuestion(2, "test2");

        criterionForQuestion1AndMustMatch = new Criterion(new Answer(question, 1), MustMatch);
        criterionForQuestion2AndMustMatch = new Criterion(new Answer(question2, 1), MustMatch);
        criterionForQuestion1AndDontCare = new Criterion(new Answer(question, 1), DontCare);
        criterionForQuestion2AndDontCare = new Criterion(new Answer(question2, 1), DontCare);

        profile = new Profile("name");
        criteria = new Criteria();
    }

    @Test
    public void returns_true_when_answers_matched_given_one_answer_and_criterion_weight_must_match() throws Exception {
        profile.add(new Answer(question, 1));
        criteria.add(criterionForQuestion1AndMustMatch);

        boolean matches =   profile.matches(criteria);

        assertThat(matches, is(true));
    }

    @Test
    public void returns_false_when_answers_not_matched_given_one_answer_and_criterion_weight_must_match() throws Exception {
        profile.add(new Answer(question, 0));
        criteria.add(criterionForQuestion1AndMustMatch);

        boolean matches = profile.matches(criteria);

        assertThat(matches, is(false));
    }

    @Test
    public void returns_true_when_answers_not_matched_given_one_answer_but_criterion_weight_not_care() throws Exception {
        profile.add(new Answer(question, 0));
        criteria.add(criterionForQuestion1AndDontCare);

        boolean matches = profile.matches(criteria);

        assertThat(matches, is(true));
    }


    @Test
    public void returns_ture_when_answers_matched_given_two_answer_and_criterion_weight_must_match() throws Exception {
        criteria.add(criterionForQuestion1AndMustMatch);
        criteria.add(criterionForQuestion2AndMustMatch);
        profile.add(new Answer(question, 1));
        profile.add(new Answer(question2, 1));

        boolean matches = profile.matches(criteria);

        assertThat(matches, is(true));
    }

    @Test
    public void returns_false_when_one_of_answer_not_matched_given_two_answer_and_criterion_weight_must_match() throws Exception {
        criteria.add(criterionForQuestion1AndMustMatch);
        criteria.add(criterionForQuestion2AndMustMatch);
        profile.add(new Answer(question, 1));
        profile.add(new Answer(question2, 0));

        boolean matches = profile.matches(criteria);

        assertThat(matches, is(false));
    }

    @Test
    public void returns_ture_when_one_of_answer_not_matched_given_two_answer_and_criterion_weight_not_care() throws Exception {
        criteria.add(criterionForQuestion1AndMustMatch);
        criteria.add(criterionForQuestion2AndDontCare);
        profile.add(new Answer(question, 1));
        profile.add(new Answer(question2, 0));

        boolean matches = profile.matches(criteria);

        assertThat(matches, is(true));
    }

    @Test
    public void returns_ture_when_answers_not_matched_given_two_answer_and_criterion_weight_not_care() throws Exception {
        criteria.add(criterionForQuestion1AndDontCare);
        criteria.add(criterionForQuestion2AndDontCare);
        profile.add(new Answer(question, 0));
        profile.add(new Answer(question2, 0));

        boolean matches = profile.matches(criteria);

        assertThat(matches, is(true));
    }

    @Test
    public void return_false_when_given_no_criterion() {
        profile.add(new Answer(question, 1));

        boolean matches = profile.matches(criteria);
        
        assertThat(matches, is(false));
    }

    @Test(expected = NullPointerException.class)
    public void throw_NullPointerExpception_when_one_answer_is_missing_given_two_answer() {
        criteria.add(criterionForQuestion1AndMustMatch);
        criteria.add(criterionForQuestion2AndMustMatch);
        profile.add(new Answer(question, 1));

        profile.matches(criteria);
    }
}