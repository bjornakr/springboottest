CREATE OR REPLACE FUNCTION trigger_set_timestamp()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updatedt = (NOW() at time zone 'utc');
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

ALTER TABLE respondents
  ADD COLUMN created TIMESTAMPTZ NOT NULL DEFAULT (NOW() at time zone 'utc'),
  ADD COLUMN updated TIMESTAMPTZ NOT NULL DEFAULT (NOW() at time zone 'utc')
;

CREATE TRIGGER set_timestamp
BEFORE UPDATE ON respondents
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();
