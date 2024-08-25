<div class="form-group row">
    {{ html()->label('Campaign Id <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('campaign_id') }}
    <div class="col-sm-10">
        <input type="text" wire:model.defer="campaignWithPriority.campaign_id" class="form-control">
        @error('campaignWithPriority.campaign_id')<x-error-message>{{$message}}</x-error-message> @enderror
    </div>

    {{ html()->label('Priority <span class="text-danger">(*)</span>')->class('col-sm-2 form-control-label')->for('priority') }}
    <div class="col-sm-10">
        <select wire:model.defer="campaignWithPriority.priority" name="priority" class="form-control">
            <option value=""></option>
            @foreach($priorityOptions as $value)
                <option value="{{ $value }}">{{ $value }}</option>
            @endforeach
        </select>
        @error('campaignWithPriority.priority')<x-error-message>{{$message}}</x-error-message> @enderror
    </div>
</div>
