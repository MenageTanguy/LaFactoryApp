import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITechnicien, defaultValue } from 'app/shared/model/technicien.model';

export const ACTION_TYPES = {
  FETCH_TECHNICIEN_LIST: 'technicien/FETCH_TECHNICIEN_LIST',
  FETCH_TECHNICIEN: 'technicien/FETCH_TECHNICIEN',
  CREATE_TECHNICIEN: 'technicien/CREATE_TECHNICIEN',
  UPDATE_TECHNICIEN: 'technicien/UPDATE_TECHNICIEN',
  DELETE_TECHNICIEN: 'technicien/DELETE_TECHNICIEN',
  RESET: 'technicien/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITechnicien>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TechnicienState = Readonly<typeof initialState>;

// Reducer

export default (state: TechnicienState = initialState, action): TechnicienState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TECHNICIEN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TECHNICIEN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TECHNICIEN):
    case REQUEST(ACTION_TYPES.UPDATE_TECHNICIEN):
    case REQUEST(ACTION_TYPES.DELETE_TECHNICIEN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TECHNICIEN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TECHNICIEN):
    case FAILURE(ACTION_TYPES.CREATE_TECHNICIEN):
    case FAILURE(ACTION_TYPES.UPDATE_TECHNICIEN):
    case FAILURE(ACTION_TYPES.DELETE_TECHNICIEN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TECHNICIEN_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TECHNICIEN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TECHNICIEN):
    case SUCCESS(ACTION_TYPES.UPDATE_TECHNICIEN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TECHNICIEN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/techniciens';

// Actions

export const getEntities: ICrudGetAllAction<ITechnicien> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TECHNICIEN_LIST,
  payload: axios.get<ITechnicien>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITechnicien> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TECHNICIEN,
    payload: axios.get<ITechnicien>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITechnicien> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TECHNICIEN,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITechnicien> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TECHNICIEN,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITechnicien> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TECHNICIEN,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
