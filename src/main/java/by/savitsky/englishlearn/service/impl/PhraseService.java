package by.savitsky.englishlearn.service.impl;

import by.savitsky.englishlearn.dto.PhraseDto;
import by.savitsky.englishlearn.mapper.PhraseMapper;
import by.savitsky.englishlearn.model.Phrase;
import by.savitsky.englishlearn.service.IPhraseService;
import by.savitsky.englishlearn.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhraseService implements IPhraseService {

    private final SessionFactory sessionFactory;

    private final PhraseMapper mapper = Mappers.getMapper(PhraseMapper.class);

    public PhraseService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void save(List<Phrase> phrases) {
        for (Phrase phrase : phrases) {
            sessionFactory.getCurrentSession().save(phrase);
        }
    }

    @Override
    @Transactional
    public void deletePhrases(List<String> pids) {
        final List<Phrase> phrases = getPhrasesByPids(pids);
        for (Phrase phrase : phrases) {
            sessionFactory.getCurrentSession().remove(phrase);
        }
    }

    @Override
    @Transactional
    public void save(Phrase phrase) {
        sessionFactory.getCurrentSession().save(phrase);
    }

    @Override
    @Transactional
    public void save(PhraseDto phraseDto) {
        final Phrase phrase = mapper.phraseDtoToPhrase(phraseDto);
        save(phrase);
    }

    @Override
    @Transactional
    public void update(Phrase phrase) {
        sessionFactory.getCurrentSession().update(phrase);
    }

    @Override
    @Transactional
    public void update(PhraseDto phraseDto) {
        final Phrase phrase = mapper.phraseDtoToPhrase(phraseDto);
        update(phrase);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Phrase> getAllPhrases() {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Phrase> cq = criteriaBuilder.createQuery(Phrase.class);
        return sessionFactory.getCurrentSession().createQuery(cq.select(cq.from(Phrase.class))).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Phrase> getPhrasesByPids(List<String> pids) {
        final CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        final CriteriaQuery<Phrase> cq = criteriaBuilder.createQuery(Phrase.class);
        final Root<Phrase> phraseRoot = cq.from(Phrase.class);
        final Expression<String> expression = phraseRoot.get("pid");
        return sessionFactory.getCurrentSession().createQuery(cq.select(phraseRoot).where(expression.in(pids))).list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PhraseDto> getAllPhraseDto() {
        return mapper.convertPhraseListToPhraseDtoList(getAllPhrases());
    }

    @Override
    @SuppressWarnings("deprecation")
    @Transactional(readOnly = true)
    public Long getPhrasesCountByCriterionList(List<Criterion> criterionList) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Phrase.class);
        criterionList.forEach(criteria::add);
        criteria.setProjection(Projections.rowCount());
        return ((Number) criteria.uniqueResult()).longValue();
    }

    @Override
    @Transactional(readOnly = true)
    public Long getPhrasesCount() {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Long> cq = criteriaBuilder.createQuery(Long.class);
        cq.select(criteriaBuilder.count(cq.from(Phrase.class)));
        return sessionFactory.getCurrentSession().createQuery(cq).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Phrase> getRandomPhrases(List<Criterion> criterionList, List<String> sqlConditions, int count) {
        final List<Phrase> result = new ArrayList<>();
        final int phrasesCount = getPhrasesCountByCriterionList(criterionList).intValue();
        final Integer[] randomValues = RandomUtil.getRandomIntUniqueValues(phrasesCount, count);
        for (int value : randomValues) {
            result.add(getRandomPhrase(value, sqlConditions));
        }
        return result;
    }

    private Phrase getRandomPhrase(int position, List<String> sqlConditions) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Phrase" + (sqlConditions.isEmpty() ? StringUtils.EMPTY : " where "
                        + String.join(", ", sqlConditions)), Phrase.class)
                .setFirstResult(position)
                .setMaxResults(1)
                .getSingleResult();
    }

}
