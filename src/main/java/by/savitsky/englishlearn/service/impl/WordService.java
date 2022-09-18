package by.savitsky.englishlearn.service.impl;

import by.savitsky.englishlearn.dto.WordDto;
import by.savitsky.englishlearn.mapper.WordMapper;
import by.savitsky.englishlearn.model.Word;
import by.savitsky.englishlearn.service.IWordService;
import by.savitsky.englishlearn.training.IFilter;
import by.savitsky.englishlearn.util.RandomUtil;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class WordService implements IWordService {

    private final SessionFactory sessionFactory;

    private final WordMapper mapper = Mappers.getMapper(WordMapper.class);

    public WordService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void save(List<Word> words) {
        for (Word word : words) {
            sessionFactory.getCurrentSession().save(word);
        }
    }

    @Override
    @Transactional
    public void deleteWords(List<String> pids) {
        final List<Word> words = getWordsByPids(pids);
        for (Word word : words) {
            sessionFactory.getCurrentSession().remove(word);
        }
    }

    @Override
    @Transactional
    public void save(Word word) {
        if (word.getVerb()) {
            if (!word.getIrregular() && !word.getPhraseVerb()) {
                final String infinitive = word.getInfinitive();
                if (infinitive.endsWith(Word.FIRST_SUFFIX)) {
                    word.setSimplePast(infinitive + Word.PAST_SUFFIX);
                    word.setPastParticiple(infinitive + Word.PAST_SUFFIX);
                } else {
                    word.setSimplePast(infinitive + Word.PAST_FULL_SUFFIX);
                    word.setPastParticiple(infinitive + Word.PAST_FULL_SUFFIX);
                }
            }
        }
        sessionFactory.getCurrentSession().save(word);
    }

    @Override
    @Transactional
    public void save(WordDto wordDto) {
        final Word word = mapper.wordDtoToSource(wordDto);
        save(word);
    }

    @Override
    @Transactional
    public void update(Word word) {
        if (word.getVerb()) {
            if (!word.getIrregular() && !word.getPhraseVerb()) {
                final String infinitive = word.getInfinitive();
                if (infinitive.endsWith(Word.FIRST_SUFFIX)) {
                    word.setSimplePast(infinitive + Word.PAST_SUFFIX);
                    word.setPastParticiple(infinitive + Word.PAST_SUFFIX);
                } else {
                    word.setSimplePast(infinitive + Word.PAST_FULL_SUFFIX);
                    word.setPastParticiple(infinitive + Word.PAST_FULL_SUFFIX);
                }
            }
        }
        sessionFactory.getCurrentSession().update(word);
    }

    @Override
    @Transactional
    public void update(WordDto wordDto) {
        final Word word = mapper.wordDtoToSource(wordDto);
        update(word);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Word> getAllWords() {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Word> cq = criteriaBuilder.createQuery(Word.class);
        return sessionFactory.getCurrentSession().createQuery(cq.select(cq.from(Word.class))).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Word> getWordsByPids(List<String> pids) {
        final CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        final CriteriaQuery<Word> cq = criteriaBuilder.createQuery(Word.class);
        final Root<Word> wordRoot = cq.from(Word.class);
        final Expression<String> expression = wordRoot.get("pid");
        return sessionFactory.getCurrentSession().createQuery(cq.select(wordRoot).where(expression.in(pids))).list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<WordDto> getAllWordDto() {
        return mapper.convertWordListToWordDtoList(getAllWords());
    }

    @Override
    @Transactional(readOnly = true)
    public Long getWordsCount() {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Long> cq = criteriaBuilder.createQuery(Long.class);
        cq.select(criteriaBuilder.count(cq.from(Word.class)));
        return sessionFactory.getCurrentSession().createQuery(cq).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("deprecation")
    public Long getWordsCountByCriterionList(List<Criterion> criterionList) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Word.class);
        criterionList.forEach(criteria::add);
        criteria.setProjection(Projections.rowCount());
        return ((Number) criteria.uniqueResult()).longValue();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Word> getRandomWords(List<Criterion> criterionList, List<String> sqlConditions, int count) {
        final List<Word> result = new ArrayList<>();
        final int wordsCount = getWordsCountByCriterionList(criterionList).intValue();
        final Integer[] randomValues = RandomUtil.getRandomIntUniqueValues(wordsCount, count);
        for (int value : randomValues) {
            result.add(getRandomWord(value, sqlConditions));
        }
        return result;
    }

    private Word getRandomWord(int position, List<String> sqlConditions) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Word" + (sqlConditions.isEmpty() ? "" : " where " + String.join(", ", sqlConditions)), Word.class)
                .setFirstResult(position)
                .setMaxResults(1)
                .getSingleResult();
    }

}
