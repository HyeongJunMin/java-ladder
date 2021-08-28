package nextstep.step4.domain;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Prizes {

	private static final String DELIMITER = ",";

	private final List<Prize> prizes;

	private Prizes(List<Prize> prizes) {
		this.prizes = new ArrayList<>(prizes);
	}

	public static Prizes from(String values) {
		List<Prize> prizes = Arrays.stream(values.split(DELIMITER))
								.map(Prize::new)
								.collect(collectingAndThen(toList(), Collections::unmodifiableList));
		return new Prizes(prizes);
	}

	public Prize of(int position) {
		return prizes.get(position);
	}

	public List<String> values() {
		return prizes.stream()
				.map(Prize::value)
				.collect(collectingAndThen(toList(), Collections::unmodifiableList));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Prizes prizes1 = (Prizes)o;
		return Objects.equals(prizes, prizes1.prizes);
	}

	@Override
	public int hashCode() {
		return Objects.hash(prizes);
	}
}