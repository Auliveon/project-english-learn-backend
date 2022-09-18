package by.savitsky.englishlearn.training;

import java.util.Optional;

public interface IFactory {

    Training create(TrainingConfig trainingConfig);

}
